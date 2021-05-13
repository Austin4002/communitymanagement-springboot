package com.gyy.boot.vo;

import lombok.Data;

@Data
public class AddFinanceVo {
    private String stuNo;

    //用途
    private String purpose;

    //金额
    private Double money;

}
