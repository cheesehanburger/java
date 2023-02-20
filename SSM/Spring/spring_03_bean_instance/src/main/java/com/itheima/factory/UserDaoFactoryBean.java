package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    // 代替原始工厂中创建对象的方法
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }
    public Class<?> getObjectType() {
        return UserDao.class;
    }
    //这个方法可以设置是否为单例
    public boolean isSingleton() {
        return false;
    }
}
