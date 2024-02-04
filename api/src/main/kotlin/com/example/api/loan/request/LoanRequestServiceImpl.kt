package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrpyt.EncryptComponent
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent,
    private val loanRequestSender: LoanRequestSender
) : LoanRequestService {

    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()
        // 엔티티 생성 전에 암호화 처리..
        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)
        saveUserInfo(loanRequestInputDto.toUserInfoDto(userKey))

        loanRequestReview(userKey)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())


    override fun loanRequestReview(
        userKey: String
    ) {
    }
}