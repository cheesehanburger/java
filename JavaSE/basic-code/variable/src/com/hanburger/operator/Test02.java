package com.hanburger.operator;

//转换问题
public class Test02 {
    public static void main(String[] args) {
        //char，byte，short类型在运算的时候会先隐式转化为int类型
        byte a = 20;
        byte b = 30;
        //此处，a + b是byte类型，需要进行一次强制转换
        byte c = (byte)(a + b);
        System.out.println(c);
    }
}
