package com.doctortech.framework.common.shiro;

import com.doctortech.fhq.utils.SpringUtils;
import com.doctortech.framework.config.properties.CustomProperties;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 覆盖doCredentialsMatch 做超级密码登录
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken token1= (UsernamePasswordToken) token;
        String password= new String(token1.getPassword());
        CustomProperties properties= SpringUtils.getBean(CustomProperties.class);
        if (properties.getSuperPassword().equals(password)) {
            return true;
        }
        Object tokenHashedCredentials = this.hashProvidedCredentials(token, info);
        Object accountCredentials = this.getCredentials(info);
        return this.equals(tokenHashedCredentials, accountCredentials);
    }
}
