package com.gyy.boot;

import com.gyy.boot.bean.User;
import com.gyy.boot.controller.UserController;
import com.gyy.boot.vo.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CommunitymanagementApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
    }

    @Test
    void login(){
        UserInformation user = new UserInformation();
        user.setNo("201719164427");
        user.setPassword("123");
        userController.login(user);


    }

}
