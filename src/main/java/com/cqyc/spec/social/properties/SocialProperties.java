package com.cqyc.spec.social.properties;

import lombok.Data;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Data
public class SocialProperties {
    private QQproperties qq = new QQproperties();
    private WeixinProperties weixin = new WeixinProperties();
    private String filterProcessesUrl =  "/auth";
}
