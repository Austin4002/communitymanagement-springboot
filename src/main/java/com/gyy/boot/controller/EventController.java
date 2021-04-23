package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.ClubEvent;
import com.gyy.boot.bean.Event;
import com.gyy.boot.service.ClubEventService;
import com.gyy.boot.service.EventService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.AddEventVo;
import com.gyy.boot.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private ClubEventService clubEventService;

    @PostMapping("/addEvent")
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
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("status");
        Page<Event> eventPage = eventService.page(page);

        if (eventPage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(eventPage);
        }



        return rs;
    }


}
