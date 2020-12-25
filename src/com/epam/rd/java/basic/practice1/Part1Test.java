package com.epam.rd.java.basic.practice1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Part1Test {
    @Test
    public void main() {
        assertEquals("Hello, World", Part1.printMessage(new String[]{"Hello, World"}));
    }
}