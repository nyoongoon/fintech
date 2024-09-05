package com.example.api.loan.request

import com.example.api.loan.GenerateKey
import com.example.api.loan.encrpyt.EncryptComponent
import com.example.domain.domain.UserInfo
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.producer.LoanRequestSender
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

//@SpringBootTest -> 너무 무거움 -> 단위테스트만 하기..
@WebMvcTest(LoanRequestController::class)
internal class LoanRequestControllerTest {
    private lateinit var mockMvc: MockMvc

    /**
     * 테스트 코드에서도 @Autowired를 사용하지 않으면 의존성에 대해 한 번 더 고민할 수 있다..
     */
    // LoanRequestController는 loanRequestService를 주입받고 있음
    // @SpringBootTest가 아니므로 -> 빈 생성 필요..
    private lateinit var loanRequestController: LoanRequestController

    private lateinit var generateKey: GenerateKey

    private lateinit var encryptComponent: EncryptComponent

    private val loanRequestSender: LoanRequestSender = mockk()

    private val userInfoRepository: UserInfoRepository = mockk() //목처리된 레포지토리가 생성된다.

    private lateinit var mapper: ObjectMapper

    @MockBean // 목처리된 빈 - 서비스
    private lateinit var loanRequestServiceImpl: LoanRequestServiceImpl

    companion object {
        private const val baseUrl = "/fintech/api/v1"
    }

    @BeforeEach
    fun init() { // 테스트코드에서도 Autowired를 안쓰면 DI가 눈에 보이고 한 번 더 고민할 수 있다..
        generateKey = GenerateKey()
        encryptComponent = EncryptComponent()
        loanRequestServiceImpl = LoanRequestServiceImpl(
            generateKey, userInfoRepository, encryptComponent, loanRequestSender
        )
        loanRequestController = LoanRequestController(loanRequestServiceImpl)
        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build()
        // 코틀린 data 기본생성자 없으므로 ObjectMapper 의존 추가해야함
        // -> 기본생성자 없어도 직렬화 역직렬화 가능
        mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
    }

    @Test
    @DisplayName("유저 요청이 들어오면 정상 응답을 주어야 한다.") //JpaAuditingConfiguration 분리해야 실행 가능...
    fun testNormalCase() {
        // given
        val loanRequestInfoDto: LoanRequestDto.LoanRequestInputDto =
            LoanRequestDto.LoanRequestInputDto(
                userName = "TEST",
                userIncomeAmount = 10000,
                userRegistrationNumber = "000101-1234567"
            )
        // mockk -> userInfoRepository.save()에 대해서 응답값을 임의로 설정
        every { userInfoRepository.save(any()) } returns UserInfo("", "", "", 1)
        // when
        // then
        mockMvc.post(
            "$baseUrl/request"
        ){
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(loanRequestInfoDto)
        }.andExpect {
            status { isOk() }
        }
    }
}