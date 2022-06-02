package com.xiangliban.managementsystem.pojo.Fix;

import lombok.Data;

@Data
public class FixOrderWithBrief {

    // Order information
    private String fixOrderId;
    private String fixId;
    // Original request information that user provides
    private String fixUserAddress;
    private String fixExpectTime;
    private String fixType;
    private String fixDetails;
    private String fixSubmitTime;
}
