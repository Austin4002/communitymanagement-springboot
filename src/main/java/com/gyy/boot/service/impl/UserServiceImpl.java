package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.User;
import com.gyy.boot.mapper.UserMapper;
import com.gyy.boot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
