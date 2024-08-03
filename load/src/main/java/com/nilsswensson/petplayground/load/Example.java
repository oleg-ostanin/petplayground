package com.nilsswensson.petplayground.load;
import java.util.Scanner;
class Example {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int x = sc.nextInt();
        if (a - b >= x || a - c >= x ) {
            if (b - a >= x || b - c >= x ) {
                if (c - a >= x || c - b >= x ) {
                    System.out.println("Ура, бастуем!");
                }
            }
        }
        else {
            System.out.println("За работу, Солнце ещё высоко");
        }




    }
}
