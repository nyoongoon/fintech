package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository
) : LoanRequestService {

    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()
        saveUserInfo(loanRequestInputDto.toUserInfoDto(userKey))
        loanRequestReview(userKey)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())


    override fun loanRequestReview(
        userKey: String
    ) {
        TODO("Not yet implemented")
    }
}