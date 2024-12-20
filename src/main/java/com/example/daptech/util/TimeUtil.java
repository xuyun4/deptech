package com.example.daptech.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
    /**
     * 生成格式为 "11/3/2014" 的日期字符串
     *
     * @return 格式化后的日期字符串
     */
    public static String getCreateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        return sdf.format(new Date());
    }

    /**
     * 生成格式为 "11/04/2014 01:21:34 AM +0000" 的日期时间字符串
     *
     * @return 格式化后的日期时间字符串
     */
    public static String getUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a Z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // 设置时区为 UTC
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        // 测试日期格式化方法
        System.out.println("日期格式: " + getCreateTime());

        // 测试日期时间格式化方法
        System.out.println("日期时间格式: " + getUpdateTime());
    }
}
