package com.hy.dynamicdatasource.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/1/21.
 */
@Mapper
public interface UserMapper {

    int insertUser(@Param("name") String name, @Param("password") String password);

    int selectUser(@Param("name") String name, @Param("password") String password);

}
