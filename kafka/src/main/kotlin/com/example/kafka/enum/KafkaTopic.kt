package com.example.kafka.enum

// 카프카 토픽은 enum으로 관리..
enum class KafkaTopic(val topicName: String) {
    LOAN_REQUEST("loan_request")
}