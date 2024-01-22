package com.example.api.test

import com.example.domain.domain.UserInfo
import com.example.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class TestService(
    private val userInfoRepository: UserInfoRepository
) {

    fun testGetService(userKey: String) = userInfoRepository.findByUserKey(userKey).toDto()

    fun UserInfo.toDto() = TestDto.UserInfoDto(userKey, userRegistrationNumber, userName, userIncomeAmount)
}