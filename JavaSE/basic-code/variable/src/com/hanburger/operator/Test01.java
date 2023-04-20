package com.hanburger.operator;

import java.util.Scanner;

//将一个三位数拆分为个位十位百位
public class Test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个三位数");
        int number = scanner.nextInt();

        for (int i = 0; i < 3; i++) {
            System.out.println(number % 10);
            number = number / 10;
        }
    }
}
