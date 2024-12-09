package com.nilsswensson.petplayground.load;

import java.util.Scanner;
class Example5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        String s = Integer.toString(a);
        int x = s.length();
        char y = s.charAt(2);
        if ((x == 3) && (b == y)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        if (x != 3) {
            System.out.println("error");
        }

    }
}