package com.example.api.loan.request

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")
class LoanRequestController (
    // 왜 인터페이스로 안 받는지..?
    private val loanRequestService: LoanRequestService
){
    @PostMapping("/request")
    fun loanRequest(
        @RequestBody loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): ResponseEntity<LoanRequestDto.LoanRequestResponseDto> {
        return ResponseEntity.ok(
            loanRequestService.loanRequestMain(loanRequestInputDto)
        )
    }
}