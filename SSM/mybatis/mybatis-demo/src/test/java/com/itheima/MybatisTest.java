package com.itheima;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Mybatis的基本CRUD
public class MybatisTest {

    @Test
    public void  testSelectAll() throws IOException {
        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void  testSelectById() throws IOException {
        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        Brand brand = brandMapper.selectById(2);
        System.out.println(brand);

        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void testselectByCondition() throws IOException {
        //接受的参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        //处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //封装对象参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);

        //封装map参数
        Map map = new HashMap();
        //map.put("status",1);
        map.put("brandName",brandName);
        map.put("companyName",companyName);

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);
        //List<Brand> brands = brandMapper.selectByCondition(brand);
        //List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void testselectByConditionSingle() throws IOException {
        //接受的参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        //处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //封装对象参数
        Brand brand = new Brand();
        brand.setStatus(status);
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);

        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //接受的参数
        int status = 1;
        String companyName = "苹果公司";
        String brandName = "苹果";
        String description = "Think different";
        int order = 100;

        //封装对象参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setDescription(description);
        brand.setOrdered(order);

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(false); // true关闭事务 false开启事务

        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        int count = brandMapper.add(brand);
        System.out.println(count);
        //开启主键返回后，可以获取主键
        Integer key = brand.getId();
        //提交事物
        sqlSession.commit();


        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //接受的参数
        int status = 1;
        String companyName = "苹果公司";
        String brandName = "苹果";
        String description = "Think different 非同凡响";
        int order = 50;
        int id = 5;

        //封装对象参数
        Brand brand = new Brand();
        brand.setStatus(0);
       // brand.setBrandName(brandName);
       // brand.setCompanyName(companyName);
       // brand.setDescription(description);
       // brand.setOrdered(order);
        brand.setId(3);

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(false); // true关闭事务 false开启事务

        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句
        int count = brandMapper.update(brand);
        System.out.println(count);
        //提交事物
        sqlSession.commit();


        //4.释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        //参数
        int[] ids = new int[] {9};

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(false); // true关闭事务 false开启事务

        // 3.获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行sql语句

        int count = brandMapper.deleteByIds(ids);
        System.out.println(count);
        //提交事物
        sqlSession.commit();


        //4.释放资源
        sqlSession.close();
    }
}
