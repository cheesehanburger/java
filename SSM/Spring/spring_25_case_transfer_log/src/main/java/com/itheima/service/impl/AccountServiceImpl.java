package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.LogDao;
import com.itheima.service.AccountService;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LogService logService;

    public void transfer(String out, String in, Double money) {

        try {
            accountDao.outMoney(out, money);
            int i = 1/0;
            accountDao.inMoney(in, money);
        } finally {
            //添加记录,保证绝对能运行
            logService.log(out, in, money);
        }
    }

}
