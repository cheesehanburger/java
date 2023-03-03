package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//在这里拦截所有层出现的异常,并且反馈给用户
//异常处理器
//@RestControllerAdvice 用于标识当前类为REST风格对应的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //异常的分类
    //业务异常 用户不规范的输入产生的异常
    //系统异常  项目运行中产生的异常
    //其他异常  不可预测的异常


    //@ExceptionHandler 设置处理什么类型的异常

    //todo 下面均为人为模拟的异常

    // 处理系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException exception) {
        //1.处理日志
        //2.发送消息给运维
        //3.发送邮件给开发人员，异常对象发送给开发人员
        return new Result(exception.getCode(), null, exception.getMessage());
    }

    //处理业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doSystemException(BusinessException exception) {
        return new Result(exception.getCode(), null, exception.getMessage());
    }

    //处理其他类的异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception) {
        //1.处理日志
        //2.发送消息给运维
        //3.发送邮件给开发人员，异常对象发送给开发人员
        return new Result(Code.SYSTEM_UNKNOW_ERR, null, "抱歉，出现了未知的错误");
    }
}
