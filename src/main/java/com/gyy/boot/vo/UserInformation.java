package com.gyy.boot.vo;

import com.gyy.boot.bean.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserInformation {

    private String no;
    private String name;
    private String password;
    private String phone;
    private List<Role> role;
    private String token;
    private String clazz;
}
