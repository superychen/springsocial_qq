package com.cqyc.spec.controller;

import com.cqyc.spec.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Controller
public class indexController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @RequestMapping
    @ResponseBody
    public Object index(){
        Map<String, String> map = new HashMap<>();
        map.put("msg","登录成功");
        return map;
    }

    @RequestMapping("cqyc-login.html")
    public String QQLogin(){
        return "cqyc-login";
    }

    @RequestMapping("cqyc-signUp.html")
    public String QQregist(){
        return "cqyc-signUp";
    }

    @PostMapping("/user/regist")
    @ResponseBody
    public void regist(SysUser sysUser, HttpServletRequest request){
        //不管是注册用户还是绑定用户，都会拿到一个用户的唯一标识
        String userId = sysUser.getLoginName();
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
    }

    @GetMapping("/user/me")
    @ResponseBody
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }

}
