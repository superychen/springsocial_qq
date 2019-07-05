package com.cqyc.spec.social.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@ConfigurationProperties(prefix = "cqyc.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
    private SocialProperties social = new SocialProperties();



}
