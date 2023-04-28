package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
//设置表名映射关系,也可以全局配置
//@TableName("tbl_user")
public class User {
    //使用雪花算法自动生成id，可以在全局配置中简化
    //@TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    @TableField(value = "pwd",select = false)
    private String password;

    private Integer age;
    private String tel;

    @TableField(exist = false)
    private Integer online;

//    逻辑删除字段，标记当前删除记录是否被删除，可以在全局配置中简化
//    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    //乐观锁的设置需要添加拦截器
    @Version
    private Integer version;

    private Data time;

}















