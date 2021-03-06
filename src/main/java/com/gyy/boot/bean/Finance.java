package com.gyy.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Finance {
    private String id;
    //社团id
    private String clubId;
    //用途
    private String purpose;
    //金额
    private Double money;
    //是否申请（0否 1是）
    private Integer isApp;
    //申请状态（0拒绝 1通过 2待审核）
    private Integer status;
    //数据更新时间
    private Date updateTime;


}