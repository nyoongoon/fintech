package com.example.kafka.dto

class LoanRequestDto(
    val userKey: String,
    val userName: String,
    val userIncomeAmount: Long,
    var userRegistrationNumber: String // 주민번호 암호화 필요..
) {

}