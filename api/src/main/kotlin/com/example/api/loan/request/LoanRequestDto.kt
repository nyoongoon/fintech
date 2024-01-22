package com.example.api.loan.request

class LoanRequestDto {
    data class LoanRequestInputDto(
        val userName: String,
        val userIncomeAmount: Long,
        val userRegistrationNumber: String
    )

    data class LoanRequestResponseDto(
        val userKey: String
    )
}