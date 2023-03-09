package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

//mybatis-plus环境下，只需要继承一个BaseMapper<T>(T为pojo类)
//mybatis内置了通用的mapper，少配置就可以进行常规的crud操作
@Mapper
public interface UserDao extends BaseMapper<User> {
}
