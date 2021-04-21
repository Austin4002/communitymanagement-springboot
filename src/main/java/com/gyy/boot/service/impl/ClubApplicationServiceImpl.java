package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.ClubApplication;
import com.gyy.boot.mapper.ClubApplicationMapper;
import com.gyy.boot.service.ClubApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ClubApplicationServiceImpl extends ServiceImpl<ClubApplicationMapper, ClubApplication> implements ClubApplicationService {
}
