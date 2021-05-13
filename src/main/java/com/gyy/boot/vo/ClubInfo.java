package com.gyy.boot.vo;

import lombok.Data;

@Data
public class ClubInfo {
    private String id;
    private String name;
    private String description;
    private Double starLevel;
    private String proprieterNo;
    private String proprieterName;
    //免申请时长
    private String period;
    //免申请金额开始时间
    private String financeStartTime;
    //免申请金额
    private Double money;
}
