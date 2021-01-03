package com.epam.rd.java.basic.practice6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Part2Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    protected ByteArrayOutputStream output;
    private PrintStream old;

    @Before
    public void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void main() {
        System.setOut(old);

        Part2 part = new Part2();
        part.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        Part2.main(new String[] {"--input", "part6.txt", "--task", "length"});
        Part2.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});

        Assert.assertNotNull(output.toString());
        System.setOut(old);


    }

    @After
    public void cleanUpStreams() {
        System.setOut(old);
        System.out.println("Code of lists is ok");
    }

}