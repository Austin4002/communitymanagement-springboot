package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.*;
import com.gyy.boot.service.*;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.ClubInfo;
import com.gyy.boot.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;

@RestController
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubUserService clubUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClubEventService clubEventService;

    @Autowired
    private SignInService signInService;

    @Autowired
    private EventService eventService;


    @GetMapping("/clubList")
    public Result<Page<Club>> getClubList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result<Page<Club>> rs = new Result<>(500, "error");
        Page<Club> page = new Page<>(current, size);
        QueryWrapper<Club> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("star_level");
        Page<Club> clubPage = clubService.page(page, wrapper);
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
        //todo ?????????????????????
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
     * ??????????????????????????????
     */
    @GetMapping("/club/userList")
    public Result getClubUserList(@RequestParam("clubId") String clubId, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");

        QueryWrapper<ClubUser> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", clubId);
        Page page = new Page<>(current, size);
        Page userPage = clubUserService.page(page, wrapper);
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


    @PostMapping("/studentJoinClub")
    public Result studentJoinClub(@RequestBody ClubUser clubUser) {
        Result rs = new Result<>(500, "error");
        QueryWrapper<ClubUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", clubUser.getUserId());
        queryWrapper.eq("club_id", clubUser.getClubId());
        List<ClubUser> clubUserList = clubUserService.list(queryWrapper);
        if (clubUserList.size() > 0) {
            rs.setCode(503);
            rs.setMsg("???????????????????????????????????????????????????");
        } else {
            clubUser.setId(IdUtils.getUUID());
            boolean flag = clubUserService.save(clubUser);
            if (flag) {
                rs.setMsg("ok");
                rs.setCode(200);
            }
        }

        return rs;
    }


    /**
     * ???????????????????????????
     *
     * @param rate       ???????????????
     * @param actionRate ???????????????
     * @return
     */
    @GetMapping("/calculateStar")
    public Result calculateStar(@RequestParam("rate") Double rate, @RequestParam("actionRate") Double actionRate) {
        Result rs = new Result<>(500, "error");
        //??????????????????
        List<Club> clubList = clubService.list();
        //??????????????????????????????
        HashMap<String, Double> signInRateMap = new HashMap<>();
        //???????????????????????????
        HashMap<String, Integer> eventTimeMap = new HashMap<>();

        for (Club club : clubList) {
            //??????????????????????????????
            QueryWrapper<ClubEvent> clubEventWrapper = new QueryWrapper<>();
            clubEventWrapper.eq("club_id", club.getId());
            //??????????????????????????????
            List<ClubEvent> clubEventList = clubEventService.list(clubEventWrapper);

            /*
            ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
            ?????????????????????????????????????????????
             */
            //????????????????????????????????????
            int eventTime = 0;
            //???????????????????????????????????????
            List<Double> clubSignInRate = new ArrayList<>();
            for (ClubEvent clubEvent : clubEventList) {
                //????????????????????????????????????
                //????????????????????????????????????
                String eventId = clubEvent.getEventId();
                Event event1 = eventService.getById(eventId);
                if (event1.getStatus() == 1) {
                    eventTime++;
                    QueryWrapper<SignIn> countWrapper = new QueryWrapper<>();
                    countWrapper.eq("event_id", eventId);

                    //?????????????????????????????????
                    int count = signInService.count(countWrapper);
                    //??????????????????????????????
                    //????????????id????????????????????????????????????????????????????????????
                    QueryWrapper<ClubEvent> clubEventWrapper2 = new QueryWrapper<>();
                    clubEventWrapper2.eq("event_id", eventId);
                    //???????????????????????????
                    List<ClubEvent> clubEventList2 = clubEventService.list(clubEventWrapper2);
                    int totalCount = 0;
                    for (ClubEvent event : clubEventList2) {
                        String clubId = event.getClubId();
                        QueryWrapper<ClubUser> clubUserWrapper = new QueryWrapper<>();
                        clubUserWrapper.eq("club_id", clubId);
                        //??????????????????????????????????????????
                        int clubUserCount = clubUserService.count(clubUserWrapper);
                        totalCount = totalCount + clubUserCount;
                    }
                    //????????????????????????
                    double signInRate = (double) count / totalCount;
                    clubSignInRate.add(signInRate);

                }


            }
            eventTimeMap.put(club.getId(), eventTime);
            //??????????????????????????????????????????
            double signInRate = clubSignInRate.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
            signInRateMap.put(club.getId(), signInRate);
        }

        QueryWrapper<Event> eventWrapper = new QueryWrapper<>();
        eventWrapper.eq("status", 1);
        //????????????????????????????????????????????????
        int eventTotalTime = eventService.count(eventWrapper);
        for (Club club : clubList) {
            String clubId = club.getId();
            Double signInRate = signInRateMap.get(clubId) * 100;
            Integer actionTime = eventTimeMap.get(clubId);
            Double actionClubRate = (double) actionTime / eventTotalTime;
            double star = (signInRate * rate + actionClubRate * 100 * actionRate) / 20;
            Club newClub = new Club();
            newClub.setId(clubId);
            newClub.setStarLevel(star);
            clubService.updateById(newClub);
        }
        rs.setCode(200);
        rs.setData("ok");


        return rs;
    }


}
