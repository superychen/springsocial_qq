package com.cqyc.spec.social.qq.connet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Slf4j
public class QQAuth2Template extends OAuth2Template {

    public QQAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);//设置为true了才会带着clientId以及clientSecret
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        log.debug("获取accessToken的响应：{}",responseStr);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr,"&");

        String acessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");

        return new AccessGrant(acessToken,null,refreshToken,expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        //先拿到父类的创建结果
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return  restTemplate;
    }
}
