package com.epam.rd.java.basic.practice1;

public class Part5 {

    public static int getSum(int n) {
        int sum = 0;
        while (n != 0 && n >= 0) {
            sum += (n % 10);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int s = Integer.parseInt(args[0]);
        System.out.print(getSum(s));
        /*int sum = 0;
        for (String arg : args) {
            try {
                int n = Integer.parseInt(arg);
                while (n != 0 && n >= 0) {
                    sum += (n % 10);
                    n /= 10;
                }
            } catch (NumberFormatException e) {
                System.out.print(arg + " is not a number");
            }
            System.out.print(sum);
        }*/
    }
}


