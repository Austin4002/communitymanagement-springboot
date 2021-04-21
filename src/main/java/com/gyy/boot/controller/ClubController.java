package com.gyy.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Club;
import com.gyy.boot.service.ClubService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/clubList")
    public Result<Page<Club>> getClubList(){
        Result<Page<Club>> rs = new Result<>(500, "error");
        Page<Club> page = new Page<>();
        Page<Club> clubPage = clubService.page(page);
        if (clubPage.getSize() >= 0){
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clubPage);
        }
        return rs;
    }

    @GetMapping("/club/{id}")
    public Result<Club> getClubById(@PathVariable("id") String id){
        Result<Club> rs = new Result<>(500, "error");
        Club club = clubService.getById(id);
        if (club != null){
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(club);
        }

        return rs;
    }

    @PutMapping("/club")
    public Result updateClubById(@RequestBody Club club){
        Result rs = new Result<>(500, "error");
        boolean flag = clubService.updateById(club);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @PostMapping("/club")
    public Result addClub(@RequestBody Club club){
        Result rs = new Result<>(500, "error");

        club.setId(IdUtils.getUUID());
        boolean flag = clubService.save(club);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }


    @DeleteMapping("/club/{id}")
    public Result deleteClubById(@PathVariable("id") String id){
        Result rs = new Result<>(500, "error");

        boolean flag = clubService.removeById(id);
        if (flag){
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

}
