package com.itheima.domain;

import lombok.*;

//使用lombok简化pojo的开发，自动生成setter和getter
//@Setter     //setter
//@Getter     //getter
//@ToString   //toString
//@NoArgsConstructor   //无参构造器
//@AllArgsConstructor  //全参构造器
//@EqualsAndHashCode   //equals方法

@Data //@Setter + @Getter + @ToString + @EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
}
