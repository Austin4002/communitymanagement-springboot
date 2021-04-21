package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.Club;
import com.gyy.boot.mapper.ClubMapper;
import com.gyy.boot.service.ClubService;
import org.springframework.stereotype.Service;

@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {
}
