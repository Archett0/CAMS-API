package com.xiangliban.managementsystem.pojo.Fix;

import lombok.Data;

@Data
public class FixOrder {
    private String fixOrderId;
    private String fixId;
    private int fixStatus;
    private String fixWorkerId;
    private String fixMaterial;
    private String fixMaterialCost;
    private String fixLaborCost;
    private String fixTimeline;
}
