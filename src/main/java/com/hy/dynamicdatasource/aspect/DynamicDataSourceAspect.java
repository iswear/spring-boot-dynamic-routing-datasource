package com.hy.dynamicdatasource.aspect;

import com.hy.dynamicdatasource.datasource.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/21.
 */

@Aspect
@Order
@Component
public class DynamicDataSourceAspect {

    @Resource
    private DynamicDataSource dynamicDataSource;

    @Around("execution(* com.ygg.pushorder.mapper..*.*(..))")
    public Object routeMapperDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        return this.dynamicDataSource.routeDataSourceByJoinpoint(joinPoint);
    }

}