package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Club;
import com.gyy.boot.bean.ClubEvent;
import com.gyy.boot.bean.Event;
import com.gyy.boot.service.ClubEventService;
import com.gyy.boot.service.ClubService;
import com.gyy.boot.service.EventService;
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

    @GetMapping("/eventList")
    public Result getEventList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");

        Page<Event> page = new Page<>(current, size);
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("status");
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
     * 社长查看活动列表，仅为活动状态为3，即正在组队中
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
        wrapper.eq("status", 3);
        Page<Event> eventPage = eventService.page(page, wrapper);

        if (eventPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(eventPage);
        }

        return rs;
    }

    /**
     * 社长查看活动详情，查看已经有哪几个社团参加了这个活动
     * @param eventId
     * @return
     */
    @GetMapping("/proprieterGetEventInfoById")
    public Result proprieterGetEventInfoById(@RequestParam("eventId") String eventId) {
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

}
