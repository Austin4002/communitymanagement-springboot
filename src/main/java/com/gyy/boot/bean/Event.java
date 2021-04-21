package com.gyy.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    //活动id
    private String id;
    //社团id
    private String clubId;
    //活动名称
    private String name;
    //活动地点
    private String place;
    // 活动1级类型
    private String type1;
    //活动2级类型
    private String type2;
    //活动开始时间
    private Date startTime;
    //活动结束时间
    private Date endTime;
    //签到表id
    private String signInId;


}