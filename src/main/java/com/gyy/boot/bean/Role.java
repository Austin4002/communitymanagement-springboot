package com.gyy.boot.bean;

import lombok.Data;

@Data
public class Role {
    //id
    private String id;
    //角色名称
    private String name;
    //描述
    private String description;
    //前端展示颜色需要
    private String type;

}