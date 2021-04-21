package com.gyy.boot.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserRole {
    //用户id
    @TableId
    private String userNo;
    //角色id
    private String roleId;

}