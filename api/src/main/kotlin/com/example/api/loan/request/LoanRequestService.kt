package com.example.api.loan.request

import com.example.domain.domain.UserInfo

// 확장성을 위해서 인터페이스로 서비스를 구현하는 편..  (컨트롤러를 인터페이스로 하는 경우도 있다..)
interface LoanRequestService {
    fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(userInfoDto: UserInfoDto): UserInfo

    fun loanRequestReview(userInfoDto: UserInfoDto)
}