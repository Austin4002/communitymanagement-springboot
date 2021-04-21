package com.gyy.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    //学号
    @TableId
    private String no;
    //密码
    private String password;
    //姓名
    private String name;
    //性别
    private String sex;
    //联系电话
    private String phone;
    //班级id
    private String classId;

}