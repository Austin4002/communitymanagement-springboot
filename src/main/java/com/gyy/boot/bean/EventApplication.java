package com.gyy.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class EventApplication {
    //id
    private String id;
    //活动id
    private String eventId;
    //管理员审核状态（0拒绝 1通过 2待审核）
    private Integer status;
    //活动审核时间
    private Date appTime;
    //申请社团1
    private String clubId1;
    //申请社团2
    private String clubId2;
    //申请社团3
    private String clubId3;


}