package com.example.api.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LogAspect {
    val logger = KotlinLogging.logger {}

    // 1. 조인포인트 가져오는 부분
    @Pointcut("within(com.example.api..*)") // 특정 시점을 가져오는 것 -> within: 패키지 내의 메서드들이 실행되는 시점으로 설정
    private fun isApi() {
    }

    // 2. 조인포인트를 가져왔을 때 어떤 부분을 할 건지
    @Around("isApi()")
    fun loggingAspect(joinPoint: ProceedingJoinPoint): Any {
        val stopWatch = StopWatch()
        stopWatch.start()

        val result = joinPoint.proceed()

        stopWatch.stop()
        logger.info { "${joinPoint.signature.name} ${joinPoint.args[0]} ${stopWatch.lastTaskTimeMillis}" }

        return result
    }
}