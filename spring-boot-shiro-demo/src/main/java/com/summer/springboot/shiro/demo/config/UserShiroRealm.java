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
import java.util.Set;

@Slf4j
public class UserShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 访问控制。比如某个用户是否具有某个操作的使用权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        log.info("==========user is {}", user);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = userService.listUserPermissions(user.getUsername());
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 用户身份识别，通常被称为用户登录
     */
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
