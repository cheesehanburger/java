package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//Mapper代理
public interface UserMapper {
    //方法的名字和映射文件对应的id同名，方法的返回值类型和参数类型一致
    List<User> selectAll();

    //注解开发
    @Select("select * from tb_user where id = #{id}")
    User selectById(int id);
}
