package com.itheima.service;

import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AccountService {
    /**
     * 转账操作
     * @param out 传出方
     * @param in 转入方
     * @param money 金额
     */

    //1.打开事务（一般会写在业务接口上，防止耦合）
    @Transactional
    public void transfer(String out,String in ,Double money) ;
}
