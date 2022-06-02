package com.xiangliban.managementsystem.service;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 杨捷宁
 * @Date: 2021/12/09/13:41
 * @Description: A simple order id constructor
 */
public class IdConsturctor {
    public static String idConsturctor() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
        return tempDate.format(new java.util.Date());
    }

//    public static void main(String[] args) {
//        IdConsturctor test = new IdConsturctor();
//        System.out.println(idConsturctor());
//    }
}
