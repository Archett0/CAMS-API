package com.xiangliban.managementsystem.service;

import org.apache.commons.lang3.RandomStringUtils;

public class NameGenerator {
    public static String nameGenerator(int genLength) {
        String name = RandomStringUtils.randomAlphanumeric(genLength);
        return name;
    }
}
