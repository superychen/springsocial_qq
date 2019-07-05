package com.cqyc.spec.social.qq.api;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%sYOUR_APP_ID&openid=%s";

    private String appId;

    private String openId;

    //openid是根据acesstoken去取出来的
    //accessToken是从spring-social从流程中获取出来的，appId是我们的配置信息
    public QQImpl(String accessToken,String appId){
        //选中这个策略的时候，会把查询参数accessToken挂到查询条件上面，默认是请求头里面
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        //openId就需要发送请求获取
        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);

        log.debug(result);

        //从返回的串里面截取一段文字出来，就是openid
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url,String.class);

        log.debug(result);

        //转换为QQUserInfo对象
        QQUserInfo qqUserInfo = JSONObject.parseObject(result, QQUserInfo.class);
        qqUserInfo.setOpenId(openId);

        return qqUserInfo;
    }

}
