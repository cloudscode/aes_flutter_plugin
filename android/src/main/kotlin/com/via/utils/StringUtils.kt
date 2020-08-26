package com.via.utils

/**
 * @author：lijian@12301.cc
 *
 * 创建时间：2020/5/15
 * 字符串工具
 */
object StringUtils {
     /**
     * 判断字符串是否为空
     * @param str
     * @return true 为空
     */
    fun isNullOrEmpty(str: String?): Boolean {
        return if (str == null || "" == str) {
            true
        } else false
    }
}