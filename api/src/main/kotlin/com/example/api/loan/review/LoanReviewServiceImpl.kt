package com.example.api.loan.review

import com.example.api.exception.CustomErrorCode
import com.example.api.exception.CustomException
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service
class LoanReviewServiceImpl(
    private val loanReviewRepository: LoanReviewRepository
) : LoanReviewService {

    override fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto {

        return LoanReviewDto.LoanReviewResponseDto(
            userKey = userKey,
            loanResult = getLoanResult(userKey)?.toResponseDto()
                ?: throw CustomException(CustomErrorCode.RESULT_NOT_FOUND) // null 인 경우
        )
    }

    /**
     * 레디스 적용할 여지가 있는 곳
     * - 키로 findBy 밖에 하는 로직만 있는데 db i/o가 계속 발생되므로
     * - 같은 유저가 계속해서 자신의 심사데이터를 요청할 때 이곳에 레디스를 적용해볼 수 있으나, 개인 별이기 때문에 히트율은 떨어지는 편이다
     * - 더 좋은 예시
     * - -> 모든 유저에게 공통의 결과를 보여주는 대출 상품 같은 것 ..!
     */
    @Cacheable(value = ["REVIEW"], key="#userKey", cacheManager = "redisCacheManager")
    override fun getLoanResult(userKey: String) =
        loanReviewRepository.findByUserKey(userKey)

    private fun LoanReview.toResponseDto() =
        LoanReviewDto.LoanResult(
            userLimitAmount = this.loanLimitedAmount,
            userLoanInterest = this.loanInterest
        )
}