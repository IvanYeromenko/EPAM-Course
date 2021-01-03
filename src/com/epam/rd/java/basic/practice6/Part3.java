package com.epam.rd.java.basic.practice6;

public class Part3 {
    public static void main(String[] args) {
        new Parking(3);

        Parking.print();
        Parking.arrive(1);
        Parking.arrive(2);
        Parking.print();
        Parking.arrive(1);
        Parking.print();
        Parking.arrive(1);
        Parking.print();

    }
}

