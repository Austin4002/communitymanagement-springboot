package com.gyy.boot.bean;

import lombok.Data;

@Data
public class Club {
    //id
    private String id;
    //社长id
    private String proprieterId;
    //社团名称
    private String name;
    //社团简介
    private String description;
    //星级
    private Double starLevel;
    //公告
    private String notice;


}