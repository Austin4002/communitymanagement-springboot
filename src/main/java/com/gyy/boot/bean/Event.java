package com.gyy.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    //活动id
    private String id;
    //活动名称
    private String name;
    //活动地点
    private String place;
    // 活动1级类型
    private String type1;
    //活动2级类型
    private String type2;
    //活动开始时间
    private String startTime;
    //活动结束时间
    private String endTime;
    //管理员审核状态（0拒绝 1通过 2待审核）
    private Integer status;
    //签到表id
    private String signInId;


}