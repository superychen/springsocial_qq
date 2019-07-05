package com.cqyc.spec.security;

import com.cqyc.spec.social.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 这是springSercurity提供的适配器类
 */
@Configuration
public class BrwserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private AuthenticationSuccessHandler cqycAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler cqycAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer cqycSpringSocialConfigurer;

    @Autowired
    private DataSource dataSource;

    //配制加密过程
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http
                .cors().and()
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/login")
//                    .successHandler(cqycAuthenticationSuccessHandler)
//                    .failureHandler(cqycAuthenticationFailureHandler)
                    .and()
                .apply(cqycSpringSocialConfigurer)
                .and()
                .authorizeRequests() //对请求授权
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist").permitAll()
                .anyRequest()  //对任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable()//跨站防护功能屏蔽掉
                ;
        //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
        http.addFilterAt(customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(cqycAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(cqycAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/authentication/login");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    //安全模块单独配置
}
