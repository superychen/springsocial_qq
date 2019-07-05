package com.cqyc.spec.social.qq.connet;

import com.cqyc.spec.social.qq.api.QQ;
import com.cqyc.spec.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 将服务提供商的用户数据和spring-social标准的数据结构做一个适配器
 */
public class QQAdapter implements ApiAdapter<QQ> {


    //测试当前的api是否可用,也就是说你QQ的服务是否还通着
    @Override
    public boolean test(QQ api) {
        //这里我就不做判断,我认为QQ请求永远是通的
        return true;
    }

    //表示你服务提供商的用户数据和connection提供的数据之间做一个适配
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        //显示出来的用户的名字
        values.setDisplayName(userInfo.getNickname());
        //拿出用户的头像,40*40
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        //这个是QQ的个人主页，但是因为QQ上面没有，所以我们这里不填
        values.setProfileUrl(null);
        //服务商的ID，就是我们一直说的用户的openID
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {
        //do nothing
    }
}
