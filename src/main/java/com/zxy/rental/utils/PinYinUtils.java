package com.zxy.rental.utils;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * @auther student_zxy
 * @date 2025/11/5
 * @project auto_rental
 */

public class PinYinUtils {
    /**
     * 获取汉字拼音首字母，并转换成大写字母
     * @param chinese 汉字
     * @return 拼音首字母
     */
    public static String getPinYin(String chinese) {
        return PinyinUtil.getFirstLetter(chinese, "").toUpperCase();
    }
}
