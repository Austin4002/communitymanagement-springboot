package com.gyy.boot.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddEventVo {

    //活动名称
    private String name;
    //活动地点
    private String place;
    // 活动1级类型
    private String type1;
    //活动2级类型
    private String type2;
    //活动开始时间
    private List<String> startAndEndTime;
    private List<String> clubIds;
    private String clubId;


}
