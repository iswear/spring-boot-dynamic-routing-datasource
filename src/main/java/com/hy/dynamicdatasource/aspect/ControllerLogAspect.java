package com.hy.dynamicdatasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/1/21.
 */
@Aspect
@Component
public class ControllerLogAspect {

    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.ygg.pushorder.controller..*.*(..))")
    public void throwException(Throwable ex) {
        ex.printStackTrace();
    }

    @Before("execution(* com.ygg.pushorder.controller..*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("hello world before");
    }

    @After("execution(* com.ygg.pushorder.controller..*.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("hello world after");
    }

}