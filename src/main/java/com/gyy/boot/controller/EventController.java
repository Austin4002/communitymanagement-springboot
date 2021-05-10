package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.*;
import com.gyy.boot.service.*;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.AddEventVo;
import com.gyy.boot.vo.EventInfoVo;
import com.gyy.boot.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private ClubEventService clubEventService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubUserService clubUserService;
    @Autowired

    private SignInService signInService;
    @Autowired
    private UserService userService;

    @PostMapping("/adminAddEvent")
    public Result adminAddEvent(@RequestBody AddEventVo eventVo) {
        Result rs = new Result<>(500, "error");
        Event event = new Event();
        BeanUtils.copyProperties(eventVo, event);
        String eventId = IdUtils.getUUID();
        event.setId(eventId);
        String startTime = eventVo.getStartAndEndTime().get(0);
        String endTime = eventVo.getStartAndEndTime().get(1);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setStatus(1);
        boolean save = eventService.save(event);
        if (save) {
            List<String> clubIds = eventVo.getClubIds();
            clubIds.forEach(v -> {
                ClubEvent clubEvent = new ClubEvent(IdUtils.getUUID(), v, eventId);
                clubEventService.save(clubEvent);
            });
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

    /**
     * 管理员查看待审核的活动列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/eventList")
    public Result getEventList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");

        Page<Event> page = new Page<>(current, size);
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 2);
        Page<Event> eventPage = eventService.page(page, wrapper);

        if (eventPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(eventPage);
        }

        return rs;
    }

    /**
     * 社长添加活动
     *
     * @param eventVo
     * @return
     */
    @PostMapping("/proprieterAddEvent")
    public Result proprieterAddEvent(@RequestBody AddEventVo eventVo) {
        Result rs = new Result<>(500, "error");
        Event event = new Event();
        BeanUtils.copyProperties(eventVo, event);

        String eventId = IdUtils.getUUID();
        event.setId(eventId);
        String startTime = eventVo.getStartAndEndTime().get(0);
        String endTime = eventVo.getStartAndEndTime().get(1);

        event.setStartTime(startTime);
        event.setEndTime(endTime);
        //设为3，活动为组队中
        event.setStatus(3);
        boolean save = eventService.save(event);
        if (save) {
            ClubEvent clubEvent = new ClubEvent(IdUtils.getUUID(), eventVo.getClubId(), eventId);
            clubEventService.save(clubEvent);
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

    /**
     * 社长查看活动列表，仅为活动状态为1,3，即正在组队中和通过审核的
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/proprieterGetEventList")
    public Result proprieterGetEventList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");
        Page<Event> page = new Page<>(current, size);
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.in("status", 1, 3);

        Page<Event> eventPage = eventService.page(page, wrapper);

        if (eventPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(eventPage);
        }

        return rs;
    }

    /**
     * 查看活动详情，查看已经有哪几个社团参加了这个活动
     *
     * @param eventId
     * @return
     */
    @GetMapping("/getEventInfoById")
    public Result getEventInfoById(@RequestParam("eventId") String eventId) {
        Result rs = new Result<>(500, "error");
        Event event = eventService.getById(eventId);
        EventInfoVo eventInfoVo = new EventInfoVo();
        BeanUtils.copyProperties(event, eventInfoVo);
        QueryWrapper<ClubEvent> wrapper = new QueryWrapper<>();
        wrapper.eq("event_id", eventId);
        List<ClubEvent> list = clubEventService.list(wrapper);
        List<Club> clubList = new ArrayList<>();
        for (ClubEvent clubEvent : list) {
            Club club = clubService.getById(clubEvent.getClubId());
            clubList.add(club);
        }
        eventInfoVo.setClubList(clubList);
        rs.setMsg("ok");
        rs.setCode(200);
        rs.setData(eventInfoVo);
        return rs;
    }


    @PutMapping("/changeEventStatus")
    public Result changeEventStatus(@RequestBody Event event) {
        Result rs = new Result<>(500, "error");
        boolean flag = eventService.updateById(event);
        if (flag) {
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    /**
     * 社长加入已经创建的活动
     *
     * @param stuNo
     * @param eventId
     * @return
     */
    @GetMapping("/proprieterJoinInEvent")
    public Result proprieterJoinInEvent(@RequestParam("stuNo") String stuNo, @RequestParam("eventId") String eventId) {
        Result rs = new Result<>(500, "error");
        //先根据学号找到社团的id
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("proprieter_id", stuNo);
        Club club = clubService.getOne(queryWrapper);
        //检查社团的id与活动的ia是否在club——event表中，即当前社团是否已经加入了活动
        QueryWrapper<ClubEvent> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", club.getId());
        wrapper.eq("event_id", eventId);
        List<ClubEvent> list = clubEventService.list(wrapper);
        if (list.size() < 1) {
            ClubEvent clubEvent = new ClubEvent(IdUtils.getUUID(), club.getId(), eventId);
            boolean flag = clubEventService.save(clubEvent);
            if (flag) {
                rs.setCode(200);
                rs.setMsg("加入成功");
            }
        } else {
            rs.setCode(503);
            rs.setMsg("当前已经加入活动了奥，请不要重复加入");
        }

        return rs;
    }

    /**
     * 学生查看自己加入社团的活动
     * @param stuNo
     * @return
     */
    @GetMapping("/studentGetEventList")
    public Result studentGetEventList(@RequestParam("stuNo") String stuNo) {
        Result rs = new Result<>(500, "error");
        //根据学号查询该学生所在的社团
        QueryWrapper<ClubUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", stuNo);
        List<ClubUser> clubUserList = clubUserService.list(queryWrapper);
        //如果学生所在的社团大于1个时，遍历每个社团。查找社团所举办的活动
        List<Event> eventList = new ArrayList<>();
        if (clubUserList.size() >= 1) {
            for (ClubUser clubUser : clubUserList) {
                QueryWrapper<ClubEvent> clubEventWrapper = new QueryWrapper<>();
                clubEventWrapper.eq("club_id", clubUser.getClubId());

                List<ClubEvent> clubEventList = clubEventService.list(clubEventWrapper);

                for (ClubEvent clubEvent : clubEventList) {
                    String eventId = clubEvent.getEventId();
//                    Event event = eventService.getById(eventId);
                    QueryWrapper<Event> eventWrapper = new QueryWrapper<>();
                    eventWrapper.eq("id",eventId);
                    eventWrapper.eq("status",1);
                    Event event = eventService.getOne(eventWrapper);
                    if (event!=null){
                        eventList.add(event);
                    }

                }
            }

            if (eventList.size() > 0) {
                rs.setCode(200);
                rs.setMsg("ok");
                rs.setData(eventList);
            } else {
                rs.setCode(503);
                rs.setMsg("暂无活动");
            }
        } else {
            rs.setCode(504);
            rs.setMsg("当前似乎没有加入社团");
        }

        return rs;
    }

    /**
     * 学生对活动进行签到
     * @param signIn
     * @return
     */
    @PostMapping("/studentSignInEvent")
    public Result studentSignInEvent(@RequestBody SignIn signIn) {
        Result rs = new Result<>(500, "error");
        QueryWrapper<SignIn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no",signIn.getUserNo());
        queryWrapper.eq("event_id",signIn.getEventId());
        List<SignIn> list = signInService.list(queryWrapper);
        if (list.size()<1){
            signIn.setId(IdUtils.getUUID());
            User user = userService.getById(signIn.getUserNo());
            signIn.setUserName(user.getName());
            boolean flag = signInService.save(signIn);
            if (flag) {
                rs.setCode(200);
                rs.setMsg("ok");
            }
        }else {
            rs.setCode(503);
            rs.setMsg("你已经签过到了，请不要重复签到");
        }

        return rs;
    }


}
