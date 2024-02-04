package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan(basePackages = ["com.example.domain"])
@ComponentScan(basePackages = ["com.example"]) // 컴포넌트 스캔 추가
class ApiApplication

fun main(args: Array<String>){
    runApplication<ApiApplication>(*args)
}