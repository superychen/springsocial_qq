package com.cqyc.spec.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.spec.domain.SysUser;
import com.cqyc.spec.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService {

    //这里将你的dao或者jpa注入到这里
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username){
        //根据用户名查找用户信息
        SysUser sysUser = userMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName, username));

        log.debug("表单登录用户名：{}",username);
        String password  = passwordEncoder.encode("123456");
        log.debug("用户从数据库查询出来的密码：{}",password);
        //根据查找到的用户信息判断用户是否被冻结

        //下面返回的是第一个和第二个参数返回的是用户名和密码，第三个表示权限，表示把字符串转换为对象
        //下面的表示true表示：第一个：用户是否可用，2: 用户是否被冻结，3：用户密码是否过期 4:是否被锁定
        return new User(sysUser.getLoginName(),sysUser.getPasswrod(),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.debug("设计登录用户id：{}",userId);
        return buildUser(userId);
    }

    public SocialUserDetails buildUser(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结

        //下面返回的是第一个和第二个参数返回的是用户名和密码，第三个表示权限，表示把字符串转换为对象
        //下面的表示true表示：第一个：用户是否可用，2: 用户是否被冻结，3：用户密码是否过期 4:是否被锁定
        String password  = passwordEncoder.encode("123456");//这个动作应该是在注册的时候做的
        log.debug("数据库加密: {}",password);
        return new SocialUser(username,password,
                true,true,true,false,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
