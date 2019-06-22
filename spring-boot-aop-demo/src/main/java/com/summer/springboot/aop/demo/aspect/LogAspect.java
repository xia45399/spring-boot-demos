package com.summer.springboot.aop.demo.aspect;

import com.summer.springboot.aop.demo.dao.SystemLogMapper;
import com.summer.springboot.aop.demo.pojo.SystemLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class LogAspect {

    @Resource
    private SystemLogMapper systemLogMapper;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.summer.springboot.aop.demo.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 方法执行前
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        System.out.println("222 doBefore");
    }

    /**
     * 方法执行后
     */
    @After("webLog()")
    public void doAfter() {
        System.out.println("555 do after");
    }

    /**
     * 环绕执行
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("111 doAround");
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息(通过logstash传入elasticsearch)
        SystemLog systemLog = new SystemLog();
        Object result = joinPoint.proceed();
        System.out.println("444");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        long endTime = System.currentTimeMillis();

        systemLog.setUrl(request.getRequestURL().toString());
        systemLog.setUri(request.getRequestURI());
        systemLog.setIp(request.getRemoteAddr());
        systemLog.setMethod(request.getMethod());
        systemLog.setStartTime(new Timestamp(startTime.get()));
        systemLog.setSpendTime((endTime - startTime.get()));

        Object o = getParameter(method, joinPoint);
        String param = o == null ? "" : o.toString();
        systemLog.setParam(param);
        systemLog.setResult(result.toString());
        systemLogMapper.insert(systemLog);
        return result;
    }

    private Object getParameter(Method method, ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("666 doAfterReturning");
    }


    @AfterThrowing(throwing = "e", pointcut = "webLog()")
    public void a(Throwable e) {
        e.printStackTrace();
    }
}
