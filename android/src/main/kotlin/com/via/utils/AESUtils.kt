package com.via.utils

import java.security.GeneralSecurityException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES工具
 */
object AESUtils {
    /**
     * aes 加密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    fun encrypt(content: String, key: String): String {
        val key=   Base64Utils.encode(key)
        val fullAlg = "AES/CBC/PKCS5Padding"
        val cipher = Cipher.getInstance(fullAlg)
        val iv =
            IvParameterSpec(
                initIv(
                    fullAlg
                )
            )
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(Base64Utils.decodeByte(key), "AES"),
            iv
        )
        val encryptBytes = cipher.doFinal(content.toByteArray(charset("UTF-8")))
        return Base64Utils.encodeByte(encryptBytes)
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws GeneralSecurityException
     */
    private fun initIv(fullAlg: String): ByteArray {
        val cipher = Cipher.getInstance(fullAlg)
        val blockSize = cipher.blockSize
        val iv = ByteArray(blockSize)
        for (i in 0 until blockSize) {
            iv[i] = 0
        }
        return iv
    }

    /**
     * @param content 密文
     * @param key aes密钥
     * @return 原文
     */
    fun decrypt(content: String, key: String): String {
        val key=   Base64Utils.encode(key)
        //反序列化AES密钥
        val keySpec =SecretKeySpec(Base64Utils.decodeByte(key), "AES")

        //128bit全零的IV向量
        val iv = ByteArray(16)
        for (i in iv.indices) {
            iv[i] = 0
        }
        val ivParameterSpec =
            IvParameterSpec(iv)

        //初始化加密器并加密
        return try {
            val deCipher =
                Cipher.getInstance("AES/CBC/PKCS5Padding")
            deCipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec)
            val encryptedBytes =
                Base64Utils.decodeByte(content)
            val bytes = deCipher.doFinal(encryptedBytes)
            String(bytes)
        } catch (e: Exception) {
            throw RuntimeException("解密失败")
        }
    }
}