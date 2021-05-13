package com.gyy.boot.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysLog {

    private String id;
//    private String no;
    private String url;
    private String method;
    private String params;
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}
