package com.hy.dynamicdatasource.service.impl;

import com.hy.dynamicdatasource.mapper.admin.UserMapper;
import com.hy.dynamicdatasource.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/21.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public int insertUser(String username, String realname) {
        int result = this.userMapper.insertUser(username, realname);
        this.userMapper.selectUser(username, realname);
        throw new RuntimeException("riririri");
    }

}
