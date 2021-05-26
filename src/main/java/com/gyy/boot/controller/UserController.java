package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.*;
import com.gyy.boot.service.*;
import com.gyy.boot.utils.CommonUtils;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.utils.TokenUtil;
import com.gyy.boot.vo.ChangeRoleVo;
import com.gyy.boot.vo.Result;
import com.gyy.boot.vo.UserInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ClubUserService clubUserService;

    @Autowired
    private ClubService clubService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<UserInformation> login(@RequestBody UserInformation user) {
        Result<UserInformation> rs = new Result<>(500, "error");
        // 根据学号密码查询数据库
        User realUser = userService.getById(user.getNo());
        if (realUser != null) {
            //发放签名
            String token = TokenUtil.sign(realUser.getName(), realUser.getPassword());
            user.setToken(token);
            //查询用户所属角色
            List<Role> roleList = commonUtils.getUserRole(user.getNo());

            user.setRole(roleList);
            user.setName(realUser.getName());
            //返回数据
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(user);
            log.info("登录成功");
        }
        return rs;

    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/userList")
    public Result<Page<User>> getUserList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result<Page<User>> rs = new Result<>(500, "error");
        //查询所有的用户列表
        Page<User> page = new Page<>(current, size);
        Page<User> userPage = userService.page(page);
        if (userPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(userPage);
        }
        return rs;
    }

    /**
     * 根据学号查询用户信息
     *
     * @param no 学号
     * @return
     */
    @GetMapping("/user/{no}")
    public Result<UserInformation> getUserById(@PathVariable("no") String no) {
        Result<UserInformation> rs = new Result<>(500, "error");

        UserInformation userInformation = new UserInformation();
        //根据学号查询用户
        User user = userService.getById(no);
        //根据用户所属班级id查询该同学所在班级
        Clazz clazz = clazzService.getById(user.getClassId());
        //根据学号查询所属角色
        List<Role> roleList = commonUtils.getUserRole(no);
        if (clazz != null && roleList.size() > 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            BeanUtils.copyProperties(user, userInformation);
            userInformation.setClazz(clazz.getName());
            userInformation.setRole(roleList);
            rs.setData(userInformation);
        }
        return rs;
    }

    @PostMapping("/changeRole")
    public Result changeRoleByNo(@RequestBody ChangeRoleVo changeRoleVo) {
        Result rs = new Result<>(500, "error");

        if (changeRoleVo.getRoleIds().size() < 1) {
            rs.setCode(502);
            rs.setMsg("用户至少拥有一个角色");
            return rs;
        } else {
            QueryWrapper<UserRole> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("user_no", changeRoleVo.getNo());
            boolean b = userRoleService.remove(deleteWrapper);
            if (b) {
                changeRoleVo.getRoleIds().forEach(v -> {
                    userRoleService.save(new UserRole(IdUtils.getUUID(), changeRoleVo.getNo(), v));
                });
                rs.setMsg("ok");
                rs.setCode(200);
            }
        }

        return rs;
    }

    @PutMapping("/updateUser")
    public Result updateUserInfo(@RequestBody User user) {
        Result rs = new Result<>(500, "error");
        boolean flag = userService.updateById(user);
        if (flag) {
            rs.setCode(200);
            rs.setData("ok");
        }

        return rs;
    }


    /**
     * 根据社团id查询社团所有用户
     *
     * @return
     */
    @GetMapping("/userClubList")
    public Result getClubUserList(@RequestParam("current") Integer current,
                                  @RequestParam("size") Integer size,
                                  @RequestParam("proprieterNo") String no) {
        Result rs = new Result<>(500, "error");
        QueryWrapper<Club> proprieterWrapper= new QueryWrapper<>();
        proprieterWrapper.eq("proprieter_id",no);
        Club club = clubService.getOne(proprieterWrapper);

        Page<ClubUser> page = new Page<>(current, size);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", club.getId());

        Page clubUserPage = clubUserService.page(page,wrapper);
        List<ClubUser> records = clubUserPage.getRecords();
        ArrayList<User> userList = new ArrayList<>();
        records.forEach(item -> {
            User user = userService.getById(item.getUserId());
            userList.add(user);
        });
        clubUserPage.setRecords(userList);

        if (clubUserPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clubUserPage);
        }
        return rs;
    }

    /**
     * 根据社长的学号，查询社团的id，然后再根据社团的id，学生的id
     * 删除学生所在的社团选报表
     * @param proprieterNo
     * @param stuNo
     * @return
     */
    @DeleteMapping("/deleteUserFromClub")
    public Result deleteUserFromClub(@RequestParam("proprieterNo") String proprieterNo,@RequestParam("stuNo") String stuNo) {
        Result rs = new Result<>(500, "error");
        QueryWrapper<Club> proprieterWrapper= new QueryWrapper<>();
        proprieterWrapper.eq("proprieter_id",proprieterNo);
        Club club = clubService.getOne(proprieterWrapper);
        String clubId = club.getId();
        QueryWrapper<ClubUser> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id",clubId);
        wrapper.eq("user_id",stuNo);
        boolean remove = clubUserService.remove(wrapper);

        if (remove) {
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

}
