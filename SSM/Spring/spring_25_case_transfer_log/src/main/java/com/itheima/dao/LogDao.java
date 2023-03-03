package com.itheima.dao;

import org.apache.ibatis.annotations.Insert;


//操作记录表
public interface LogDao {
    @Insert("insert into tbl_log (info,createDate) values(#{info},now())")
    void addLog(String info);
}
