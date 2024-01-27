package com.example.api.loan.encrpyt

import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component //어노테이션으로 만드는 방법 사용하면 편하다고 한다...
class EncryptComponent { //AES256 방식 사용..
    //키값 따로 관리 안하는 방식임..
    companion object {
        private const val secretKey = "12345678901234561234567890123456"
    }

    private val encoder = Base64.getEncoder()
    private val decoder = Base64.getDecoder()

    fun encryptString(encryptString: String): String {
        val encryptedString = cipherPkcs5(Cipher.ENCRYPT_MODE, secretKey).doFinal(encryptString.toByteArray(Charsets.UTF_8))
        return String(encoder.encode(encryptedString))
    }

    fun decryptString(decryptString: String): String {
        val byteString = decoder.decode(decryptString.toByteArray(Charsets.UTF_8))
        return String(cipherPkcs5(Cipher.DECRYPT_MODE, secretKey).doFinal(byteString))
    }

    fun cipherPkcs5(opMode: Int, secretKey: String): Cipher {
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        //iv값 필요.. -> 따로 지정하는 케이스, 키값에서 일부분 떼와서 사용하는 케이스도 있음..
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opMode, sk, iv)
        return c;
    }
}