package com.example.domain.repository

import com.example.domain.domain.LoanReview
import org.springframework.data.jpa.repository.JpaRepository

interface LoanReviewRepository : JpaRepository<LoanReview, Long>{
    fun findByUserKey(userKey: String): LoanReview?
}