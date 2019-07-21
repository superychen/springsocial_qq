package com.cqyc.spec.social.weixin.api;

/**
 * 微信API调用接口
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
