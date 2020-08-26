package com.via.utils

import android.util.Base64


/**
 * Base64工具类
 * @author：lijian@12301.cc
 * @createdate：2020/5/16
 *
 */
object Base64Utils {

    /**
     * 加密
     */
    fun encode(data: String): String {
        val base64 = Base64.encode(data.toByteArray(), Base64.DEFAULT)
        var result = String(base64)
        return replaceBlank(result)
    }

    fun encodeByte(data: ByteArray): String {
        val base64 = Base64.encode(data, Base64.DEFAULT)
        var result = String(base64)
        return replaceBlank(result)
    }

    /**
     * 解密
     */
    fun decode(data: String): String {
        val base64 = Base64.decode(data.toByteArray(), Base64.DEFAULT)
        return String(base64)
    }

    fun decodeByte(data: String): ByteArray {
        val base64 = Base64.decode(data.toByteArray(), Base64.DEFAULT)
        return base64
    }

    /**
     * 替换空格
     */
    fun replaceBlank(result: String): String {
        if (StringUtils.isNullOrEmpty(result)) {
            return result
        }
        return result.replace("\n", "")
    }
}