package com.summer.springboot.shiro.demo.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 自定义shiro拦截器
 */
@Slf4j
public class ShiroAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("==================拦截器方法,url=" + ((HttpServletRequest) request).getRequestURI());
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequest(request);
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            JSONObject json = new JSONObject();
            json.put("msg", "请重新登录！");
            response.getWriter().write(json.toJSONString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
    }

}
