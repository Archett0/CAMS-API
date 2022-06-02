package com.xiangliban.managementsystem.pojo.Fix;

import lombok.Data;

@Data
public class FixOrderWithDetails {

    // Order information
    private String fixOrderId;
    private String fixId;
    private int fixStatus;
    private String fixWorkerId;
    private String fixMaterial;
    private String fixMaterialCost;
    private String fixLaborCost;
    private String fixTimeline;

    // Original request information that user provides
    private String fixUserId;
    private String fixUserName;
    private String fixUserPhone;
    private String fixUserAddress;
    private String fixUserDoor;
    private String fixExpectTime;
    private String fixType;
    private String fixDetails;
    private String fixPicture;
    private String fixSubmitTime;

    // Data collected by joint query
    private String fixDepartmentId;
    private String fixDepartmentName;
    private String fixWorkerName;
    private String fixWorkerPhone;
}
