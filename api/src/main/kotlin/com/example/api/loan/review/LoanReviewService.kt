package com.example.api.loan.review

import com.example.domain.domain.LoanReview

interface LoanReviewService {
    fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto
    fun getLoanResult(userKey: String): LoanReview?
}