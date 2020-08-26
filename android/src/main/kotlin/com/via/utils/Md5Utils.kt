package com.via.utils

import java.security.MessageDigest

/**
 * pft拓展函数类
 * @author：lijian@12301.cc
 * @createdate：2020/5/16
 */
object Md5Utils {
    /**
     * MD5 加密
     * @param sign
     * @return
     */
    fun getMD5(sign: String): String {
        var md5: MessageDigest = MessageDigest.getInstance("MD5")
        md5.update(sign.toByteArray())
        val b = md5.digest()
        val buf = StringBuffer()
        var i: Int
        for (offset in b.indices) {
            i = b[offset].toInt()
            if (i < 0) i += 256
            if (i < 16) buf.append("0")
            buf.append(Integer.toHexString(i))
        }
        var sign = buf.toString().toLowerCase()
        return sign
    }
}