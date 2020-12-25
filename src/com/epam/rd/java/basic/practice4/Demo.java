package com.epam.rd.java.basic.practice4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Demo {

    //public static final String ENCODING = "Cp1251";

    private static final InputStream STD_IN = System.in;

    //public static final String FILE_NAME_PART3 = "part3.txt";

    //public static final String RESOURCES_BASE_NAME = "resources";


    public static void main(final String[] args) {

        // part1
        System.out.println("=========================== PART1");
        Part1.main(args);
        System.out.println();

        // part2
        System.out.println("=========================== PART2");
        Part2.main(args);
        System.out.println();

        // part3
        System.out.println("=========================== PART3");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "char^String^int^double^stop".replace("^", System.lineSeparator()).getBytes()));
        Part3.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        // part4
        System.out.println("=========================== PART4");
        Part4.main(args);

        // part5
        System.out.println("=========================== PART5");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "table ru^table en^apple ru^stop".replace("^", System.lineSeparator()).getBytes()));
        Part5.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        // part6
        System.out.println("=========================== PART6");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "Latn^Cyrl^asdf^latn^cyrl^stop".replace("^", System.lineSeparator()).getBytes()));
        Part6.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }
}
