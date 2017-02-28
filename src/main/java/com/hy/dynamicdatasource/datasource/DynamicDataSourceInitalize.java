package com.hy.dynamicdatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/10.
 */
@Configuration
public class DynamicDataSourceInitalize {


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "route-data-source")
    DynamicDataSource getDataSource() {
        return DynamicDataSource.createInstance(DruidDataSource.class);
    }


}
