package com.nilsswensson.petplayground.load;

import java.util.Scanner;
class Example4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        String s = Integer.toString(a);
        int x = s.length();
        char y = s.charAt(1);
        int yAsChar = y;
        int yCorrect = Integer.parseInt(String.valueOf(y));
        if (x != 3) {
            System.out.println("error");
        } else {
            if (b == y) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }
}
