package com.hanburger.circulate;

import java.util.Scanner;

//除数问题
//输入一个被除数，和一个除数，要求不能使用乘法，除法和%，得出商和余数
public class Test02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入被除数：");
        int num1 = scanner.nextInt(); //被除数
        System.out.print("输入除数：");
        int num2 = scanner.nextInt(); //除数

        int shang = 0;
        while (num1 >= num2) {
            num1 = num1 -num2;
            shang ++;
        }
        System.out.println("商是：" + shang);
        System.out.println("余数是：" + num1);
    }
}
