package com.nilsswensson.petplayground.load;
import java.util.Scanner;

class Example3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        // тут ты сначала проверяешь, что хотя бы одно из них четное и если да, ищешь наибольшее
        if (a % 2 == 0 || b % 2 == 0 || c % 2 == 0) {
            if (a % 2 == 0 && b % 2 != 0 && c % 2 != 0) {
                System.out.println(a);
            }
            if (b % 2 == 0 && c % 2 != 0 && a % 2 != 0) {
                System.out.println(b);
            }
            if (c % 2 == 0 && a % 2 != 0 && b % 2 != 0) {
                System.out.println(c);
            }
            if (a % 2 == 0 && b % 2 == 0 && c % 2 != 0) {
                System.out.println(Math.max(a, b));
            }
            if (b % 2 == 0 && c % 2 == 0 && a % 2 != 0) {
                System.out.println(Math.max(b, c));
            }
            if (a % 2 == 0 && c % 2 == 0 && b % 2 != 0) {
                System.out.println(Math.max(a, c));
            }
            if (a % 2 == 0 && b % 2 == 0 && c % 2 == 0) {
                if (a > b && a > c) {
                    System.out.println(a);
                }
                if (b > a && b > c) {
                    System.out.println(b);
                }
                if (c > a && c > b) {
                    System.out.println(c);
                }
            }
        } else {
            System.out.println("Чётных чисел нет");
        }

    }
}
