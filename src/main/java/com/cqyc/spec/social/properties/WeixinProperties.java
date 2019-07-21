package com.cqyc.spec.social.properties;

import lombok.Data;

/**
 * @Description: 微信登录配置类
 * @Author:
 * @Date:
 */
@Data
public class WeixinProperties  {
    private String appId;

    private String appSecret;

    private String providerId="weixin";
}
