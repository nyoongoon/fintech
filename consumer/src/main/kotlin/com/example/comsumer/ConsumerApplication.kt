package com.example.comsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * 별도 구동 서버
 */
@SpringBootApplication
@EntityScan(basePackages = ["com.example.domain"])
@ComponentScan(basePackages = ["com.example"]) // 컴포넌트 스캔 추가 (멀티모듈이슈처리)
class ConsumerApplication

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}