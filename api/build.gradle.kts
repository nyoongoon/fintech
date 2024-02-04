plugins {}

version = "0.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation(project(":domain"))
    implementation(project(":kafka"))
    // 목케이
    testImplementation("io.mockk:mockk:1.12.0")
    // 테스트용 db는 로컬에서 하는 것이 좋음
    runtimeOnly("com.h2database:h2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")

    // AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
}
