package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.Clazz;
import com.gyy.boot.mapper.ClazzMapper;
import com.gyy.boot.service.ClazzService;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

}
