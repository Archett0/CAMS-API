package com.xiangliban.managementsystem.service;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/14/01:41
 * @Description: A simple time stamp generator
 */

public class CurrentTimeGenerator {
    public static String timeGenerator() {
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(new java.util.Date());
    }
}
