package com.epam.rd.java.basic.practice3;

public final class Part5 {

    private static final String[] REGEX_ROMAN = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",
            "V", "IV", "I" };

    private static final int[] ROMAN_NUMERALS = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    /**
     * Default constructor.
     */
    private Part5() {
        throw new UnsupportedOperationException("Subtask05");
    }

    public static String decimal2Roman(int digit) {
        int x = digit;

        final StringBuilder s = new StringBuilder();

        for (int i = 0; i < REGEX_ROMAN.length; i++) {
            while (x >= ROMAN_NUMERALS[i]) {
                s.append(REGEX_ROMAN[i]);
                x -= ROMAN_NUMERALS[i];
            }
        }
        return s.toString();
    }

    public static void main(String[] args) {
        final int numberFor = 200;
        for (int i = 1; i <= numberFor; i++) {
            System.out.println(i + " ==> " + decimal2Roman(i) + " ==> " + roman2Decimal(decimal2Roman(i)));
        }

    }

    public static int roman2Decimal(String digit) {
        final StringBuilder roman = new StringBuilder(digit);

        int constNum = 0;
        int number = 0;
        /* для каждой римской цифры */
        while (roman.length() != 0) {
            /* для повторяющейся римской цифры */
            if (roman.indexOf(REGEX_ROMAN[number]) == 0) {
                constNum += ROMAN_NUMERALS[number];
                roman.delete(0, REGEX_ROMAN[number].length());
                continue;
            }
            number++;
        }
        return constNum;
    }

}
