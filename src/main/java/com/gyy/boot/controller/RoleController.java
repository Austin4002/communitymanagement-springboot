package com.gyy.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Role;
import com.gyy.boot.service.RoleService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.Result;
import com.gyy.boot.vo.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roleList")
    public Result<Page<Role>> getRoleList(@RequestParam("current") Integer current, @RequestParam("size") Integer size){
        Result<Page<Role>> rs = new Result<>(500, "error");
        Page<Role> page = new Page<Role>(current,size);
        Page<Role> rolePage = roleService.page(page);
//        log.info("page:{}",rolePage.getRecords().toString());
        if (rolePage.getSize() >= 0){
            rs.setData(rolePage);
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @GetMapping("/role/{id}")
    public Result<Role> getRoleById(@PathVariable("id") String id){
        Result<Role> rs = new Result<>(500, "error");

        Role role = roleService.getById(id);
        if (role!=null){
            rs.setMsg("ok");
            rs.setCode(200);
            rs.setData(role);
        }
        return rs;
    }

    @PutMapping("/role")
    public Result updateRoleById(@RequestBody Role role){
        Result rs = new Result<>(500, "error");
        boolean flag = roleService.updateById(role);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;

    }

    @PostMapping("/role")
    public Result addRole(@RequestBody Role role){
        Result rs = new Result<>(500, "error");
        role.setId(IdUtils.getUUID());
        boolean flag = roleService.save(role);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @DeleteMapping("/role/{id}")
    public Result deleteRoleById(@PathVariable("id") String id){
        Result rs = new Result<>(500, "error");

        boolean flag = roleService.removeById(id);
        if (flag){
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

}
