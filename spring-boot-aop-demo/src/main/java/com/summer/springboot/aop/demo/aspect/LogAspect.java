package com.summer.springboot.aop.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class LogAspect {

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

        systemLog.setIp(request.getRemoteUser());
        systemLog.setMethod(request.getMethod());
        systemLog.setResult(result.toString());
        systemLog.setSpendTime((endTime - startTime.get()));
        systemLog.setStartTime(startTime.get());
        systemLog.setUri(request.getRequestURI());
        systemLog.setUrl(request.getRequestURL().toString());

        return result;
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
