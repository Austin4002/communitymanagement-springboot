package com.gyy.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRole {

    private String id;
    //用户id
    private String userNo;
    //角色id
    private String roleId;

}