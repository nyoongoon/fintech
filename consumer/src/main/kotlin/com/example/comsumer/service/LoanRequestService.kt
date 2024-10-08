package com.example.comsumer.service

import com.example.comsumer.dto.ReviewResponseDto
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import com.example.kafka.dto.LoanRequestDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {
    companion object {
        const val nginxUrl = "http://nginx:8085/css/api/v1/request"
    }

    /**
     * CB Component로 요청 보내기 -> 응답값을 DB에 저장하기
     */
    fun loanRequest(loanRequestDto: LoanRequestDto) {
        println("request to nginx...")
        val reviewResult = loanRequestToCb(loanRequestDto)
        println("nginx request complete...")

        println("request to kafka...")
        saveLoanReviewData(reviewResult.toLoanReviewEntity())
        println("nginx request complete..")
    }

    private fun loanRequestToCb(loanRequestDto: LoanRequestDto): ReviewResponseDto {
        /**
         * restTemplate 같은 외부 통신은 모듈로 분리하는 게 좋긴 함
         */
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(1000))
            .setReadTimeout(Duration.ofMillis(1000))
            .build();

        return restTemplate.postForEntity(nginxUrl, loanRequestDto, ReviewResponseDto::class.java ).body!!
    }

    private fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)
}