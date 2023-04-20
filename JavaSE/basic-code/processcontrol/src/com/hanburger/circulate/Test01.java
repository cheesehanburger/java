package com.hanburger.circulate;

import java.util.Scanner;

//判断回文数
public class Test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int temp = num;
        int renum = 0;
        while (temp != 0) {
            renum = renum * 10 + temp % 10;
            temp = temp / 10;
        }
        System.out.println(renum);
        if (renum == num) {
            System.out.println("是回文数");
        } else {
            System.out.println("不是回文数");
        }
    }
}
