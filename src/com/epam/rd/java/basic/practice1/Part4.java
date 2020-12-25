package com.epam.rd.java.basic.practice1;

public class Part4 {
    public static int getNod(int a,int b) {
        while (b !=0) {
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }
    //Evklid google
    public static void main(String[] args) {

        int i1 = Integer.parseInt(args[0]);
        int i2 = Integer.parseInt(args[1]);
        System.out.print(getNod(i1, i2));

    }
}