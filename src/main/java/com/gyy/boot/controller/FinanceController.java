package com.gyy.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyy.boot.bean.Club;
import com.gyy.boot.bean.Finance;
import com.gyy.boot.service.ClubService;
import com.gyy.boot.service.FinanceService;
import com.gyy.boot.utils.IdUtils;
import com.gyy.boot.vo.AddFinanceVo;
import com.gyy.boot.vo.FinanceVo;
import com.gyy.boot.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class FinanceController {

    @Autowired
    private FinanceService financeService;
    @Autowired
    private ClubService clubService;

    @GetMapping("/financeList")
    public Result getFinancetList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");
        Page<Finance> page = new Page<>(current, size);
        QueryWrapper<Finance> wrapper = new QueryWrapper<>();
        wrapper.in("status", 1, 2);
        wrapper.orderByDesc("status");
        Page financePage = financeService.page(page, wrapper);

        List<Finance> financeList = financePage.getRecords();
        List<FinanceVo> arrayList = new ArrayList<>();

        for (Finance finance : financeList) {
            FinanceVo financeVo = new FinanceVo();
            BeanUtils.copyProperties(finance,financeVo);
            Club club = clubService.getById(finance.getClubId());
            financeVo.setName(club.getName());
            arrayList.add(financeVo);
        }
        financePage.setRecords(arrayList);

        if (financePage.getSize() >= 0) {
            rs.setCode(200);
            rs.setMsg("ok");
            rs.setData(financePage);
        }
        return rs;
    }

    @PutMapping("/clubFianceSetting")
    public Result clubFianceSetting(@RequestBody Club club) {
        Result rs = new Result<>(500, "error");
        boolean flag = clubService.updateById(club);
        if (flag) {
            rs.setMsg("ok");
            rs.setCode(200);
        }
        return rs;
    }

    /**
     * 社长申请经费
     *
     * @param addFinanceVo
     * @return
     */
    @PostMapping("/proprieterApplyFinance")
    public Result proprieterApplyFinance(@RequestBody AddFinanceVo addFinanceVo) {
        Result rs = new Result<>(500, "error");
        //根据社长学号，查询社团信息
        String no = addFinanceVo.getStuNo();
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("proprieter_id", no);
        Club club = clubService.getOne(queryWrapper);

        if (club != null && club.getFinanceStartTime() != null && club.getPeriod() != null) {
            Date financeStartTime = club.getFinanceStartTime();
            String period = club.getPeriod();

            //获取免申请周期时间
            Calendar cal = Calendar.getInstance();
            cal.setTime(financeStartTime);
            if (period.equals("一周")) {
                cal.add(Calendar.DAY_OF_WEEK, 1);
            } else if (period.equals("一个月")) {
                cal.add(Calendar.MONTH, 1);
            } else if (period.equals("三个月")) {
                cal.add(Calendar.MONTH, 3);
            } else if (period.equals("一年")) {
                cal.add(Calendar.YEAR, 1);
            }
            Date endTime = cal.getTime();
            //判断当前社团从申请周期开始之后的所有的金额是否超过了免申请金额
            QueryWrapper<Finance> financeWrapper = new QueryWrapper<>();
            financeWrapper.eq("club_id", club.getId());
            financeWrapper.ge("update_time", financeStartTime);
            financeWrapper.le("update_time", endTime);
            List<Finance> financeList = financeService.list(financeWrapper);
            //计算从免申请金额开始时间以来到结束的所有的申请记录是否超过了免申请金额，
            double total = 0;
            for (Finance finance : financeList) {
                total = total + finance.getMoney();
            }
            Finance finance = new Finance();
            finance.setId(IdUtils.getUUID());
            finance.setClubId(club.getId());
            finance.setPurpose(addFinanceVo.getPurpose());
            finance.setMoney(addFinanceVo.getMoney());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            finance.setUpdateTime(new Date());
            //如果没有超过免申请金额
            if (total < club.getMoney()) {
                //判断加上当前申请的金额是否超过
                if ((total + addFinanceVo.getMoney()) < club.getMoney()) {
                    //如果没有超过,当前记录不需要审核，直接设置状态为通过，否申请
                    finance.setStatus(1);
                    finance.setIsApp(0);
                } else {
                    //如果超过,设状态为待审核，是申请
                    finance.setStatus(2);
                    finance.setIsApp(1);
                }
            } else {
                //如果超过了
                finance.setStatus(2);
                finance.setIsApp(1);
            }
            //将记录添加到finance表中
            boolean flag = financeService.save(finance);
            if (flag){
                rs.setCode(200);
                rs.setMsg("ok");
            }
        }else {
            rs.setCode(503);
            rs.setMsg("请检查当前身份是否为社长，或联系管理员查询有无免申请金额");
        }
        return rs;
    }


    /**
     * 社长查看本社团所有的经费记录
     * @param no
     * @return
     */
    @GetMapping("/proprieterGetFinanceList")
    public Result proprieterGetFinanceList(@RequestParam String no,@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Result rs = new Result<>(500, "error");

        QueryWrapper<Club> clubWrapper = new QueryWrapper<>();
        clubWrapper.eq("proprieter_id",no);
        Club club = clubService.getOne(clubWrapper);

        QueryWrapper<Finance> financeWrapper = new QueryWrapper<>();
        financeWrapper.eq("club_id",club.getId());
        financeWrapper.orderByAsc("status","update_time");
        Page<Finance> page = new Page<>(current,size);
        Page<Finance> financePage = financeService.page(page, financeWrapper);
        if (financePage.getSize() >= 0){
            rs.setData(financePage);
            rs.setCode(200);
            rs.setMsg("ok");
        }

        return rs;
    }

    @PutMapping("/changeFinanceStatus")
    public Result changeFinanceStatus(@RequestBody Finance finance){
        Result rs = new Result<>(500, "error");
        boolean flag = financeService.updateById(finance);
        if (flag) {
            rs.setCode(200);
            rs.setMsg("ok");
        }

        return rs;
    }

}
