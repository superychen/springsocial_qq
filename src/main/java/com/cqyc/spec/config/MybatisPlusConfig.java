package com.cqyc.spec.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cqyc.spec.comm.JDBCProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@Configuration
@EnableConfigurationProperties(JDBCProperties.class)
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 配置阿里巴巴数据库
     */
    @Bean
    public DataSource dataSource(JDBCProperties jdbcProperties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setUsername(jdbcProperties.getUsername());
        return dataSource;
    }

}
