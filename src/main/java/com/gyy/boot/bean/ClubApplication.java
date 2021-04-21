package com.gyy.boot.bean;

import lombok.Data;

@Data
public class ClubApplication {
    //申请id
    private String id;
    //学生学号
    private String userNo;
    //社团id
    private String clubId;
    //申请状态（0拒绝 1通过 2待审核）
    private Integer status;
    //详细描述
    private String description;

}