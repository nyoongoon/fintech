package com.example.comsumer.kafka

import com.example.comsumer.service.LoanRequestService
import com.example.kafka.dto.LoanRequestDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class LoanRequestConsumer (
    private val objectMapper: ObjectMapper,
    private val loanRequestService: LoanRequestService
    //TODO : CB 사 호출 로직
){
    /**
     * 토픽과 그룹아이디를 지정하면 오프셋에 따라서 카프카 토픽을 읽는다
     */
    @KafkaListener(topics = ["loan_request"], groupId = "fintech")
    fun loanRequestTopicConsumer(message: String){
        val loanRequestKafkaDto = objectMapper.readValue(message, LoanRequestDto::class.java)

        loanRequestService.loanRequest(loanRequestKafkaDto)
    }
}