package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.ClubEvent;
import com.gyy.boot.mapper.ClubEventMapper;
import com.gyy.boot.service.ClubEventService;
import org.springframework.stereotype.Service;

@Service
public class ClubEventServiceImpl extends ServiceImpl<ClubEventMapper, ClubEvent> implements ClubEventService {
}
