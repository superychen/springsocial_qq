package com.cqyc.spec.social.qq.connet;

import com.cqyc.spec.social.qq.api.QQ;
import com.cqyc.spec.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Description:
 * @Author:
 * @Date:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        //下面的四个参数分别表示,1,2两个表示用户在QQ互联中获取的appId以及appSecret,
        // 第三个参数对应的是spring social流程的第一步将用户导向认证服务器，第四个表示认证服务器申请令牌
        super(new QQAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }

}
