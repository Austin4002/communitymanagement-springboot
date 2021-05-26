package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.SysLog;
import com.gyy.boot.service.SysLogService;
import com.gyy.boot.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/logList")
    public Result getLogList(@RequestParam("current") Integer current, @RequestParam("size") Integer size){
        Result rs = new Result<>(500, "error");
        Page<SysLog> page = new Page<>(current,size);
        QueryWrapper<SysLog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("time");
        Page<SysLog> logPage = sysLogService.page(page,wrapper);
        if (logPage.getSize() >= 0){
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(logPage);
        }
        return rs;
    }
}
