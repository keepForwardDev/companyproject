package com.doctortech.fhq.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doctortech.fhq.bean.Router;
import com.doctortech.fhq.entity.jpa.business.User;
import com.doctortech.fhq.entity.jpa.common.Menu;
import com.doctortech.fhq.entity.jpa.common.Resource;
import com.doctortech.fhq.entity.jpa.common.Role;
import com.doctortech.fhq.repository.mapper.business.UserMapper;
import com.doctortech.fhq.repository.mapper.common.MenuMapper;
import com.doctortech.fhq.repository.mapper.common.ResourceMapper;
import com.doctortech.fhq.repository.mapper.common.RoleMapper;
import com.doctortech.fhq.utils.SpringUtils;
import com.doctortech.framework.common.shiro.ShiroUser;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserAuthService {

    @Autowired
    private UserMapper userDao;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private ResourceMapper resourceDao;

    public User getUserByAccount(String account) {
        QueryWrapper<User> condition = new QueryWrapper<>();
        condition.eq("account", account);
        return userDao.selectOne(condition);
    }

    public static UserAuthService me() {
        return SpringUtils.getBean(UserAuthService.class);
    }

    public ShiroUser getShiroUser(User user) {
        assertUser(user);
        ShiroUser shiroUser = new ShiroUser();
        BeanUtils.copyProperties(user, shiroUser);
        //获取角色
        List<Role> roles = roleDao.getUserRoles(user.getId());
        //角色id
        List<Long> rolesId = new ArrayList<>();
        roles.forEach(role -> {
            shiroUser.getRolesCode().add(role.getCode());
            rolesId.add(role.getId());
        });
        if (rolesId.isEmpty()) {
            return shiroUser;
        }
        //获取角色菜单
        List<Router> menus = menuService.getUserRouters(rolesId);
        shiroUser.setMenu(menus);
        //获取角色资源
        List<Resource> resources = resourceDao.getRoleResources(rolesId);
        resources.forEach(resource -> {
            shiroUser.getResourcesCode().add(resource.getCode());
        });
        return shiroUser;
    }


    private void assertUser(User user) {
        if (null == user) { //不存在提示账号密码错误
            throw new CredentialsException();
        }

        if (user.getEnabled() == 0) {//禁用提示账号禁用
            throw new LockedAccountException();
        }

    }

    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
