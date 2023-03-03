package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    //TODO 查询表
    List<Brand> selectAll();

    //单条件查询
    Brand selectById(int id);

    //TODO 多条件查询(有三个方法)
    //1散装参数：如果方法种有多个参数，需要使用@Param指定值会给到哪个"SQL参数占位符名称"
    //我们在接口方法中定义多个参数，Mybatis 会将这些参数封装成 Map 集合对象，值就是参数值，而键在"没有使用@Param注解"时有以下命名规则：
    /*
    map.put("arg0"，参数值1);
    map.put("arg1"，参数值2);
    map.put("param1"，参数值1);
    map.put("param2"，参数值2);
    而arg会被@Param替换，但是param则不会被代替
    */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    //2对象参数: 对象属性的属性名称要和参数占位符的名称相同
    List<Brand> selectByCondition(Brand brand);

    //3map集合参数
    List<Brand> selectByCondition(Map map);

    //TODO 单条件查询（动态sql）
    List<Brand> selectByConditionSingle(Brand brand);

    //TODO 添加数据
    int add(Brand brand);

    //TODO 修改数据
    int update(Brand brand);

    //TODO 删除数据（单条）
    int deleteById(int id);

    //TODO  批量删除
    int deleteByIds(@Param("ids") int[] ids);
}
