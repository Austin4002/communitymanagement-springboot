package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.Finance;
import com.gyy.boot.mapper.FinanceMapper;
import com.gyy.boot.service.FinanceService;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {

}
