package com.xiangliban.managementsystem.service;

import com.xiangliban.managementsystem.pojo.Fix.FixTimeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/14/16:41
 * @Description: Analysis timeline and return processed result
 */

public class TimelineProcessor {
    public static List<FixTimeline> processTimeline(String rawData) {
        List<FixTimeline> result = new ArrayList<FixTimeline>();
        String[] splitData = rawData.split("%");
        for (String currentData : splitData) {
            String timeNode = currentData.substring(0, 19);
            String eventNode = currentData.substring(20);
            FixTimeline newNode = new FixTimeline(timeNode, eventNode);
            result.add(newNode);
        }
        return result;
    }
}
