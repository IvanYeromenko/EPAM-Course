package com.epam.rd.java.basic.practice1;
import java.util.InputMismatchException;

import java.util.Arrays;

public class Part6 {
    public static void main(String[] args) {

        for(String arg : args){
            try {
                int size = Integer.parseInt(arg);
                System.out.println(Arrays.toString(arrayOfPrimeNumbers(size)));
            } catch (InputMismatchException exception) {
                exception.printStackTrace();
            }
        }
        }

    private static int[] arrayOfPrimeNumbers(int size) {

        int[] result = new int[size];
        int k = 0;
        int number = 2;//простые числа стартуют с 2

        while (k != size) {
            if (isPrime(number)) {
                result[k] = number;
                k++;
            }
            number++;
        }

        return result;
    }

    private static boolean isPrime(int number) {

        for (int prime = 2; prime < number; prime++) {
            if (number % prime == 0) {
                return false;
            }
        }
        return true;
    }

}
