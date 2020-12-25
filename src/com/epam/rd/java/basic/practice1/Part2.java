package com.epam.rd.java.basic.practice1;

public class Part2 {

    public static int getSum(int sum,int b) {
        sum += b;
        return sum;
    }

    public static void main(String[] args) {

        int i1 = Integer.parseInt(args[0]);
        int i2 = Integer.parseInt(args[1]);
        System.out.print(getSum(i1, i2));

    }

    /*public static void main(String[] args){
        int sum = 0;
        // проверка всех аргументов
        for(String arg : args)
        {
            try
            {
                // пробуем перевести все аргументы в целые числа
                int number = Integer.parseInt(arg);
                // сумируем
                sum += number;
            }
            catch(NumberFormatException e)
            {
                // если аргумент не может быть преобразован в число
                System.out.println(arg + " is not a number");
            }
        }
        System.out.print(sum);
    }*/
}