package com.xiangliban.managementsystem.pojo.Fix;

import lombok.Data;

@Data
public class FixRequest {
    private String fixId;
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

    public FixRequest(String fixId, String fixUserId, String fixUserName, String fixUserPhone, String fixUserAddress, String fixUserDoor, String fixExpectTime, String fixType, String fixDetails, String fixPicture, String fixSubmitTime) {
        this.fixId = fixId;
        this.fixUserId = fixUserId;
        this.fixUserName = fixUserName;
        this.fixUserPhone = fixUserPhone;
        this.fixUserAddress = fixUserAddress;
        this.fixUserDoor = fixUserDoor;
        this.fixExpectTime = fixExpectTime;
        this.fixType = fixType;
        this.fixDetails = fixDetails;
        this.fixPicture = fixPicture;
        this.fixSubmitTime = fixSubmitTime;
    }
}