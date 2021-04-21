package com.gyy.boot.controller;

import com.gyy.boot.vo.Menu;
import com.gyy.boot.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MenuController {

    @GetMapping("/menu")
    public Result<Menu[]> getMenuList(String role){
        Result<Menu[]> rs = new Result<>(500, "error");
        log.info("当前角色为----------------- >{}",role);
        if (role != null){
            rs.setCode(200);
            rs.setMsg("ok");
            if (role.equals("admin")){ //管理员

                Menu[] menu2_1 = new Menu[] {
                        new Menu("/home/permission/roleList", "角色列表", "el-icon-menu"),
                        new Menu("/home/permission/behavior", "行为日志", "el-icon-menu")
                };
                Menu[] menu2_2 = new Menu[] {
                        new Menu("/home/userManagement/list", "用户列表", "el-icon-menu"),
                        new Menu("/home/userManagement/class", "班级列表", "el-icon-menu"),
                        new Menu("/home/userManagement/club", "社团列表", "el-icon-menu")
                };
                Menu[] menu2_3= new Menu[] {
                        new Menu("/home/eventManagement/approve", "活动审批", "el-icon-menu"),
                        new Menu("/home/eventManagement/newEvent", "活动创建", "el-icon-menu")
                };
                Menu[] menu2_4= new Menu[] {
                        new Menu("/home/moneyManagement/in", "入账管理", "el-icon-menu"),
                        new Menu("/home/moneyManagement/out", "出账管理", "el-icon-menu")
                };
                // 一级菜单
                Menu[] menu = new Menu[] {
                        new Menu("/home/permission", "权限管理", "el-icon-user-solid", menu2_1),
                        new Menu("/home/userManagement", "用户管理", "el-icon-user-solid", menu2_2),
                        new Menu("/home/eventManagement", "活动管理", "el-icon-user-solid", menu2_3),
                        new Menu("/home/moneyManagement", "经费管理", "el-icon-user-solid", menu2_4),
                };
                rs.setData(menu);
            } else if (role.equals("user")){ //普通学生
                Menu[] menu = new Menu[] {
                        new Menu("/home/personal", "个人中心", "el-icon-user-solid"),
                        new Menu("/home/announcement", "查看公告", "el-icon-user-solid"),
                        new Menu("/home/eventNotice", "活动通知", "el-icon-user-solid"),
                        new Menu("/home/star", "个人中心", "el-icon-user-solid")
                };
                rs.setData(menu);
            }else if (role.equals("proprieter")) { //社长
                Menu[] menu3_1 = new Menu[] {
                        new Menu("/home/proprieter/eventManagement/application", "活动申请", "el-icon-menu")
                };
                Menu[] menu = new Menu[] {
                        new Menu("/home/proprieter/personal", "个人中心", "el-icon-user-solid"),
                        new Menu("/home/proprieter/announcement", "公告管理", "el-icon-user-solid"),
                        new Menu("/home/proprieter/eventManagement", "活动管理", "el-icon-user-solid",menu3_1),
                        new Menu("/home/proprieter/moneyManagement", "经费管理", "el-icon-user-solid")
                };
                rs.setData(menu);
            }else if (role.equals("deputy director")) { //副社长

            }

        }

        return rs;
    }

}
