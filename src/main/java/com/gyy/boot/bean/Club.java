package com.gyy.boot.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    //免申请时长
    private String period;
    //免申请金额开始时间
    //todo 这里格式转换还是有bug
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date financeStartTime;
    //免申请金额
    private Double money;


}