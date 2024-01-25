package com.example.api.loan

import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateKey {
    // UUID의 - 값 생략하기..
    fun generateUserKey() = UUID.randomUUID().toString().replace("-", "")
}