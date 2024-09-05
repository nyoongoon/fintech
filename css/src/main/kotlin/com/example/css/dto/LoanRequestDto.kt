package com.example.css.dto

class LoanRequestDto {
    data class RequestInputDto(
        val userKey: String,
        val userName: String,
        val userIncomeAmount: Long,
        var userRegistrationNumber: String
    )
}