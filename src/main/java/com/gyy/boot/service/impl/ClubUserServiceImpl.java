package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.ClubUser;
import com.gyy.boot.mapper.ClubUserMapper;
import com.gyy.boot.service.ClubUserService;
import org.springframework.stereotype.Service;

@Service
public class ClubUserServiceImpl extends ServiceImpl<ClubUserMapper, ClubUser> implements ClubUserService {
}
