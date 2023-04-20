package com.hanburger.operator;


//字符串的+操作
public class Test03 {
    public static void main(String[] args) {
        //出现了字符串则+号变为连接符
        System.out.println("abc" + 1 + 2); //abc12
        //没有出现字符串+号仍为运算符
        System.out.println(1 + 2 + "abc"); //3abc
    }
}
