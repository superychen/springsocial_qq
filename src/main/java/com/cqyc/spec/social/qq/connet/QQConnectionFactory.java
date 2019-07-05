package com.cqyc.spec.social.qq.connet;

import com.cqyc.spec.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Description:
 * @Author:
 * @Date:
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    //这三个参数表示：1：providerId：提供商的唯一标识，利用配置文件把它配进来
    //2:serviceProvider就是我们刚才写的QQServiceProvider
    public QQConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
