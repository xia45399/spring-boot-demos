package com.summer.springboot.shiro.demo.config;


import com.summer.springboot.shiro.demo.pojo.User;
import com.summer.springboot.shiro.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

@Slf4j
public class UserShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object obj = principals.getPrimaryPrincipal();
        log.info("==========user is {}", obj);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 字符串形式的权限，shiro还支持role形式的
        info.addStringPermission("student:getStudentById");
        // TODO 用户授权信息
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (StringUtils.isNotEmpty(username)) {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            } else {
                log.warn("用户不存在:" + username);
                throw new UnknownAccountException();
            }
        }
        return null;
    }
}
