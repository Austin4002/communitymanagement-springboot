package com.gyy.boot.aop;

import com.alibaba.fastjson.JSONObject;
import com.gyy.boot.bean.SysLog;
import com.gyy.boot.service.SysLogService;
import com.gyy.boot.utils.IdUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("execution(* com.gyy.boot.controller.*.*(..))")
    public void logPointCut(){}


    @Before( value = "logPointCut()")
    public void saveLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        sysLog.setId(IdUtils.getUUID());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String method = request.getMethod();
        sysLog.setMethod(method);
        //获取请求ip
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);
        String url = request.getRequestURL().toString();
        sysLog.setUrl(url);

        //获取方法名
        String methodName = joinPoint.getSignature().getName();
//        sysLog.setMethod(method);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysLog.setTime(date);

        Map<String, String[]> parameterMap = request.getParameterMap();
        String mapStr = JSONObject.toJSONString(parameterMap);

        sysLog.setParams(mapStr);
        boolean flag = sysLogService.save(sysLog);


    }

}
