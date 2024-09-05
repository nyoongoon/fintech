package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@EntityScan(basePackages = ["com.example.domain"])
@ComponentScan(basePackages = ["com.example"]) // 컴포넌트 스캔 추가 (멀티모듈이슈처리)
@EnableCaching
class ApiApplication

fun main(args: Array<String>){
    runApplication<ApiApplication>(*args)
}