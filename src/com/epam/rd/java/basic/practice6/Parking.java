package com.epam.rd.java.basic.practice6;

public class Parking {
    private static int capacity;
    private static StringBuilder parkingState;

    public Parking(int capacity) {
        Parking.capacity = capacity;

        StringBuilder initialState = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            initialState.append("0");
        }
        parkingState = initialState;
    }

    public static boolean arrive(int k) {
        if (k < 0 || k > capacity) {
            throw new IllegalArgumentException("k is outside the range [0, n-1]");
        }
        if (parkingState.charAt(k) == '0') {
            parkingState.replace(k, k + 1, "1");
            return true;
        }
        if (isAvailableOnTheRight(k + 1)) {
            return true;
        }
        return isAvailableOnTheLeft(k);
    }

    public boolean depart(int k) {
        if (k < 0 || k > capacity) {
            throw new IllegalArgumentException("k is outside the range [0, n-1]");
        }
        if (parkingState.charAt(k) == '1') {
            parkingState.replace(k, k + 1, "0");
            return true;
        }
        return false;
    }

    public static void print() {
        System.out.print(parkingState.toString() + System.lineSeparator());
    }

    private static boolean isAvailableOnTheRight(int start) {
        for (int i = start; i < capacity; i++) {
            if (parkingState.charAt(i) == '0') {
                parkingState.replace(i, i + 1, "1");
                return true;
            }
        }
        return false;
    }

    private static boolean isAvailableOnTheLeft(int end) {
        for (int i = 0; i < end; i++) {
            if (parkingState.charAt(i) == '0') {
                parkingState.replace(i, i + 1, "1");
                return true;
            }
        }
        return false;
    }
}

/* public class Parking {
    private static int capacity;
    private static StringBuilder parkingState;

    public Parking(int capacity) {
        Parking.capacity = capacity;

        StringBuilder initialState = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            initialState.append("0");
        }
        parkingState = initialState;
    }

    public static boolean arrive(int k) {
        if (k < 0 || k > capacity) {
            throw new IllegalArgumentException("k is outside the range [0, n-1]");
        }
        if (parkingState.charAt(k) == '0') {
            parkingState.replace(k, k + 1, "1");
            return true;
        }
        if (isAvailableOnTheRight(k + 1)) {
            return true;
        }
        return isAvailableOnTheLeft(k);
    }

    public boolean depart(int k) {
        if (k < 0 || k > capacity) {
            throw new IllegalArgumentException("k is outside the range [0, n-1]");
        }
        if (parkingState.charAt(k) == '1') {
            parkingState.replace(k, k + 1, "0");
            return true;
        }
        return false;
    }

    public static void print() {
        System.out.print(parkingState.toString() + System.lineSeparator());
    }

    private static boolean isAvailableOnTheRight(int start) {
        for (int i = start; i < capacity; i++) {
            if (parkingState.charAt(i) == '0') {
                parkingState.replace(i, i + 1, "1");
                return true;
            }
        }
        return false;
    }

    private static boolean isAvailableOnTheLeft(int end) {
        for (int i = 0; i < end; i++) {
            if (parkingState.charAt(i) == '0') {
                parkingState.replace(i, i + 1, "1");
                return true;
            }
        }
        return false;
    }
}
*/