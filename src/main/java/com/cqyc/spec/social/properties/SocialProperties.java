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
    private String filterProcessesUrl =  "/auth";
}
