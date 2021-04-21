package com.gyy.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Clazz;
import com.gyy.boot.service.ClazzService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 查询所有班级
     * @return
     */
    @GetMapping("/classList")
    public Result<Page<Clazz>> getClazzList(){
        Result<Page<Clazz>> rs = new Result<>(500, "error");
        Page<Clazz> page = new Page<>();
        Page<Clazz> clazzPage = clazzService.page(page);
        if (clazzPage.getSize() >= 0){
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clazzPage);
        }
        return rs;
    }

    @GetMapping("/class/{id}")
    public Result<Clazz> getClazzById(@PathVariable("id") String id){
        Result<Clazz> rs = new Result<>(500, "error");
        Clazz clazz = clazzService.getById(id);
        if (clazz != null){
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(clazz);
        }

        return rs;
    }

    @PutMapping("/class")
    public Result updateClazzById(@RequestBody Clazz clazz){
        Result rs = new Result<>(500, "error");
        boolean flag = clazzService.updateById(clazz);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @PostMapping("/class")
    public Result addClazz(@RequestBody Clazz clazz){
        Result rs = new Result<>(500, "error");
        clazz.setId(IdUtils.getUUID());
        boolean flag = clazzService.save(clazz);
        if (flag){
            rs.setCode(200);
            rs.setMsg("ok");
        }
        return rs;
    }

    @DeleteMapping("/class/{id}")
    public Result deleteClassById(@PathVariable("id") String id){
        Result rs = new Result<>(500, "error");
        boolean flag = clazzService.removeById(id);
        if (flag){
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }


}
