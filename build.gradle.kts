import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.0" apply false

    id("org.springframework.boot") version "2.6.3" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

repositories {
    mavenCentral()
}

allprojects{
    group = "com.example"
    repositories {
        mavenCentral()
    }
}

subprojects{
    apply{
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
    tasks.withType<KotlinCompile>{
        kotlinOptions{
            freeCompilerArgs = listOf("-Xjsr-305=strict")
            jvmTarget = "11"
        }
    }
    tasks.withType<Test>{
        useJUnitPlatform()
    }
    dependencies{
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

/**
 * 멀티 모듈 설정하기
 */
project(":api") {
    dependencies {
        implementation(project(":domain"))
        implementation(project(":kafka"))
    }
}

project(":consumer") {
    dependencies {
        implementation(project(":domain"))
        implementation(project(":kafka"))
    }
}

project(":css") {
}

/**
 * 실행파일이 없다면 명시를 해줘야 에러가 발생하지 않음!
 */
project(":domain") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true
}

project(":kafka") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true
}