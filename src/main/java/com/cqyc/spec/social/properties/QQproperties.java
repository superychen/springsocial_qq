package com.cqyc.spec.social.properties;

import lombok.Data;

/**
 * QQ配置
 */
@Data
public class QQproperties{
    private String appId;

    private String appSecret;

    private String providerId = "qq";
}
