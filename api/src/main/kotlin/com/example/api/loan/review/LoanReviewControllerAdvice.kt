package com.example.api.loan.review

import com.example.api.exception.CustomException
import com.example.api.exception.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [LoanReviewController::class])
class LoanReviewControllerAdvice { // exception 처리 -> 1. ControllerAdvice 2. ExceptionHandler
    // 특정 에러코드별로 처리할 때 @ExceptionHandler 사용..
    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(customException: CustomException) =
        ErrorResponse(customException).toResponseEntity()
}