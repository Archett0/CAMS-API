package com.xiangliban.managementsystem.pojo.Fix;

public class FixTimeline {
    private String timeNode;
    private String eventNode;

    public FixTimeline() {
    }

    public FixTimeline(String timeNode, String eventNode) {
        this.timeNode = timeNode;
        this.eventNode = eventNode;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getEventNode() {
        return eventNode;
    }

    public void setEventNode(String eventNode) {
        this.eventNode = eventNode;
    }
}
