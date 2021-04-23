package com.gyy.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.gyy.boot.mapper")
@ServletComponentScan("com.gyy.boot")
public class CommunityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityManagementApplication.class, args);
        System.out.println("项目启动完成........");
    }

}
