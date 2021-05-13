package com.gyy.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Log {

    private String id;
    private String no;
    private String url;
    private String method;
    private String params;
    private String ip;
    private Date time;

}
