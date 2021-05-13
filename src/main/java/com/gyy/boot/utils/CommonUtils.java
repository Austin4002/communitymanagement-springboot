package com.gyy.boot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.boot.bean.Role;
import com.gyy.boot.bean.UserRole;
import com.gyy.boot.service.RoleService;
import com.gyy.boot.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@Component
public class CommonUtils {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    public List<Role> getUserRole(String no){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_no",no);
        List<UserRole> userRoleList = userRoleService.listByMap(columnMap);

        List<String> ids = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            String roleId = userRole.getRoleId();
            ids.add(roleId);
        }
        return roleService.listByIds(ids);
    }

    public String objToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
