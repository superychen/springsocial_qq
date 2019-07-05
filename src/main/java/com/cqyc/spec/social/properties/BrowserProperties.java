package com.cqyc.spec.social.properties;

import lombok.Data;

/**
 *
 */
@Data
public class BrowserProperties {
    private String signUpUrl="/cqyc-signUp.html";
    private String loginPage = "/cqyc-login.html";
    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;//一小时
}
