package com.example.comsumer.dto

import com.example.domain.domain.LoanReview

data class ReviewResponseDto(
    val userKey: String,
    val interest: Double,
    val limitAmount: Long
) {
    fun toLoanReviewEntity(): LoanReview =
        LoanReview(
            userKey = userKey,
            loanInterest = interest,
            loanLimitedAmount = limitAmount
        )
}