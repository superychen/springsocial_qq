package com.cqyc.spec.social.qq;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识,可以不用返回的用户名作为唯一标识
        return connection.getDisplayName();
    }

}
