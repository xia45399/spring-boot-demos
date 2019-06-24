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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;

@Aspect
@Component
@Order(1)
/**
 * 先doAround,(joinPoint.proceed之前)
 * 后doBefore
 * 切面方法
 * doAround (异常没有此步)
 * doAfter
 * AfterReturning (异常没有此步)
 * AfterThrowing   （异常执行）
 */
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

        String param;
//        Object o = getParameter(method, joinPoint);
//        param = o == null ? "" : o.toString();
        param = getParam(method, joinPoint.getArgs());

        systemLog.setParam(param);
        systemLog.setResult(result.toString());
        systemLogMapper.insert(systemLog);
        return result;
    }

    private String getParam(Method method, Object[] args) {
        Parameter[] params = method.getParameters();
        int len = params.length;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Parameter p = params[i];
            String key = p.getName();
            Object v = args[i];
            s.append(key).append(":").append(v).append(" ");
        }
        return s.toString();
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
