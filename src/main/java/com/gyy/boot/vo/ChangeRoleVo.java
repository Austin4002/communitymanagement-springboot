package com.gyy.boot.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChangeRoleVo {
    private String no;
    private List<String> roleIds;

}
