package com.example.api.loan.request

class LoanRequestDto {
    data class LoanRequestInputDto(
        val userName: String,
        val userIncomeAmount: Long,
        val userRegistrationNumber: String
    ){
        fun toUserInfoDto(userKey: String) =
            UserInfoDto(
                userKey, userName, userRegistrationNumber, userIncomeAmount
            )
    }

    data class LoanRequestResponseDto(
        val userKey: String
    )
}