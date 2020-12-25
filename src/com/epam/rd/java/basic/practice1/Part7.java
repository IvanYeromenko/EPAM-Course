package com.epam.rd.java.basic.practice1;

public class Part7 {
    private static final int SYSTEM = 26;
    private static final int START_SYMBOL = 64;

    public static void main(String[] args) {
        System.out.println("A ==> 1 ==> A");
        System.out.println("B ==> 2 ==> B");
        System.out.println("Z ==> 26 ==> Z");
        System.out.println("AA ==> 27 ==> AA");
        System.out.println("AZ ==> 52 ==> AZ");
        System.out.println("BA ==> 53 ==> BA");
        System.out.println("ZZ ==> 702 ==> ZZ");
        System.out.println("AAA ==> 703 ==> AAA");
    }

    public static int str2int(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            double symbol = number.charAt(i);
            double symbolDig = symbol - START_SYMBOL;// 1 -26
            sum += Math.pow(SYSTEM, number.length() - 1 - i) * symbolDig;
        }
        sum += Math.pow(SYSTEM, 0) - 1;
        return sum;
    }

    public static String int2str(int number) {
        StringBuilder str = new StringBuilder();


        while (number > SYSTEM) {

            int cel = number / SYSTEM - (number % 26 == 0 ? 1 : 0);
            int ost = number - cel * SYSTEM;
            ost = (ost == 0 ? 26 : ost);
            number = cel;
            str.append((char) (ost + START_SYMBOL));
        }
        str.append((char) (number + START_SYMBOL));
        return str.reverse().toString();
    }

    public static String rightColumn(String number) {
        return int2str(str2int(number) + 1);
    }
}
