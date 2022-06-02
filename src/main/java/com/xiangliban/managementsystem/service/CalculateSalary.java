package com.xiangliban.managementsystem.service;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class CalculateSalary {
    public static String calculateSalary(String rawData) throws ParseException {
        // 先获取工作人员上门时间并转换格式
        String tmpData = TimelineProcessor.processTimeline(rawData).get(2).getTimeNode();
        String startTime = tmpData.replace(".", "-");
        // 获取当前时间
        String currentTime = CurrentTimeGenerator.timeGenerator();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 再次转换格式准备计算
        Date startDate = sdf.parse(startTime);
        Date endDate = sdf.parse(currentTime);
        // 算出毫秒级时间差
        long milliTime = endDate.getTime() - startDate.getTime();
        int hourTime = (int) milliTime / 3600000;
        // 通过计算出的时间算出费用并返回
        hourTime = (hourTime == 0) ? 5 : hourTime * 5;
        return Integer.toString(hourTime)+"元";
    }
}
