package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

//lombok
@Data
//mp默认的是将pojo类的小写当作表名，而@TableName则是将表名映射到pojo类上
@TableName("tbl_user")
public class User {
    private Long id;
    private String name;

    //映射类名,并且隐藏该字段
    @TableField(value = "pwd",select = false)
    private String password;
    private Integer age;
    private String tel;

    //外挂一个原表不存在的字段
    @TableField(exist = false)
    private Integer online;
}
