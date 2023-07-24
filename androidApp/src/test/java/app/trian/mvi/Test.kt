package app.trian.mvi

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class Test {

    @Test
    fun `get Days of week`(){

        val inputText = "{data: {\"source\" : \"APEKA\", \"kodeuser\" : \"SI172130\"}}"
        val algorithm = "AES/CBC/PKCS5Padding"
        val key = SecretKeySpec("1234567890123456".toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(16))

        val cipherText = encrypt(algorithm, inputText, key, iv)
        val plainText = decrypt(algorithm, cipherText, key, iv)


        assertEquals(inputText,cipherText)

    }


    fun decrypt(algorithm: String, cipherText: String, key: SecretKeySpec, iv: IvParameterSpec): String {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(plainText)
    }

    fun encrypt(algorithm: String, inputText: String, key: SecretKeySpec, iv: IvParameterSpec): String {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val cipherText = cipher.doFinal(inputText.toByteArray())
        return Base64.getEncoder().encodeToString(cipherText)
    }


}