package com.cqyc.spec.domain;

import lombok.Data;

/**
 * 前台显示QQ登录的用户数据
 */
@Data
public class SocialUserInfo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headimg;
}
