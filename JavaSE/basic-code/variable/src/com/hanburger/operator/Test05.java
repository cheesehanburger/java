package com.hanburger.operator;

public class Test05 {
    public static void main(String[] args) {
        //+= -= /= *= 这类运算符中隐藏了强制类型转换
        short s = 1;
        s += 1;
        //等同于 s = (short) (s + 1)
    }
}
