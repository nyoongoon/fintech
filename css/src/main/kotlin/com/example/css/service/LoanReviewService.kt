package com.example.css.service

import com.example.css.dto.LoanRequestDto
import com.example.css.dto.LoanResultDto
import org.springframework.stereotype.Service

@Service
class LoanReviewService {
    fun loanReview(loanResultDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto {
        if (loanResultDto.userIncomeAmount < 0) throw RuntimeException("invalid userIncomeAmount Param")
        if (loanResultDto.userIncomeAmount < 1000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            0.0,
            1000000
        )
        if (loanResultDto.userIncomeAmount < 2000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            10.0,
            2000000
        )
        if (loanResultDto.userIncomeAmount < 3000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            9.0,
            3000000
        )
        if (loanResultDto.userIncomeAmount < 4000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            8.0,
            4000000
        )
        if (loanResultDto.userIncomeAmount < 5000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            7.0,
            5000000
        )
        if (loanResultDto.userIncomeAmount >= 6000000) return LoanResultDto.ResponseDto(
            loanResultDto.userKey,
            6.0,
            6000000
        )
        throw RuntimeException("invalid userIncomeAmount Param")
    }
}