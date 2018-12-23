package com.doctortech.framework.common.shiro;

import com.doctortech.fhq.entity.jpa.business.User;
import com.doctortech.fhq.service.common.UserAuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shiro 用户DB查询
 */
public class ShiroDbRealm extends AuthorizingRealm {


    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UserAuthService userService= UserAuthService.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.getUserByAccount(token.getUsername());
        ShiroUser shiroUser = userService.getShiroUser(user);
        return userService.info(shiroUser, user, super.getName());
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserAuthService userService= UserAuthService.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(shiroUser.getResourcesCode());
        info.addRoles(shiroUser.getRolesCode());
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new MyHashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
