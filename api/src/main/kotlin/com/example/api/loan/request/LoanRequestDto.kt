package com.example.api.loan.request

class LoanRequestDto {
    data class LoanRequestInputDto(
        val userName: String,
        val userIncomeAmount: Long, // 소득
        var userRegistrationNumber: String // 주민번호 암호화 필요..
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