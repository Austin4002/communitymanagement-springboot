package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.SignIn;
import com.gyy.boot.mapper.SignInMapper;
import com.gyy.boot.service.SignInService;
import org.springframework.stereotype.Service;

@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements SignInService {
}
