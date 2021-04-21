package com.gyy.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyy.boot.bean.UserRole;
import com.gyy.boot.mapper.UserRoleMapper;
import com.gyy.boot.service.UserRoleService;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
