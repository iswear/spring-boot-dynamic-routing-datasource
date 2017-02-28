package com.hy.dynamicdatasource.controller;

import com.hy.dynamicdatasource.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/20.
 */
@RestController
public class HZHSCallback {


    @Resource
    private UserService userService;

    @RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
    public String receiveNotificationFromHZHS(
            ) {
        this.userService.insertUser("yayayaya", "yayayaya");
        return "hello world";
    }


}
