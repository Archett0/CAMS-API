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
}