package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Club;
import com.gyy.boot.bean.ClubUser;
import com.gyy.boot.bean.User;
import com.gyy.boot.service.ClubService;
import com.gyy.boot.service.ClubUserService;
import com.gyy.boot.service.UserService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.ClubInfo;
import com.gyy.boot.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubUserService clubUserService;

    @Autowired
    private UserService userService;


    @GetMapping("/clubList")
    public Result<Page<Club>> getClubList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result<Page<Club>> rs = new Result<>(500, "error");
        Page<Club> page = new Page<>(current, size);
        Page<Club> clubPage = clubService.page(page);
        if (clubPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clubPage);
        }
        return rs;
    }

    @GetMapping("/club/{id}")
    public Result<ClubInfo> getClubById(@PathVariable("id") String id) {
        Result<ClubInfo> rs = new Result<>(500, "error");
        Club club = clubService.getById(id);

        ClubInfo clubInfo = new ClubInfo();
        BeanUtils.copyProperties(club, clubInfo);
        String proprieterId = club.getProprieterId();
        if (proprieterId != null && !proprieterId.equals("")) {
            User user = userService.getById(proprieterId);

            clubInfo.setProprieterNo(user.getNo());
            clubInfo.setProprieterName(user.getName());
        } else {
            clubInfo.setProprieterNo("");
            clubInfo.setProprieterName("");
        }


        if (club != null) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clubInfo);
        }

        return rs;
    }

    @PutMapping("/club")
    public Result updateClubById(@RequestBody Club club) {
        Result rs = new Result<>(500, "error");
        //todo 社长怎么指定？
        boolean flag = clubService.updateById(club);
        if (flag) {
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @PostMapping("/club")
    public Result addClub(@RequestBody Club club) {
        Result rs = new Result<>(500, "error");

        club.setId(IdUtils.getUUID());
        boolean flag = clubService.save(club);
        if (flag) {
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }


    @DeleteMapping("/club/{id}")
    public Result deleteClubById(@PathVariable("id") String id) {
        Result rs = new Result<>(500, "error");

        boolean flag = clubService.removeById(id);
        if (flag) {
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

    /**
     * 查询该社团的学生信息
     */
    @GetMapping("/club/userList")
    public Result getClubUserList(@RequestParam("clubId") String clubId, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");

        QueryWrapper<ClubUser> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", clubId);
        Page page = new Page<>(current, size);
        Page userPage = clubUserService.page(page);
        List<ClubUser> clubUserList = userPage.getRecords();

        List<User> userList = new ArrayList<>();
        clubUserList.forEach(v -> {
            String userId = v.getUserId();
            User user = userService.getById(userId);
            userList.add(user);
        });

        userPage.setRecords(userList);

        rs.setCode(200);
        rs.setMsg("ok");
        rs.setData(userPage);
        return rs;
    }

    @GetMapping("/getClubByProprieter/{proprieterId}")
    public Result getClubByProprieter(@PathVariable("proprieterId") String proprieterId) {
        Result rs = new Result<>(500, "error");

        QueryWrapper<Club> wrapper = new QueryWrapper<>();
        wrapper.eq("proprieter_id", proprieterId);
        Club club = clubService.getOne(wrapper);
        if (club != null) {
            rs.setData(club);
            rs.setCode(200);
            rs.setMsg("ok");
        }


        return rs;
    }
}
