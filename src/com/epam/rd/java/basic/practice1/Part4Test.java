package com.epam.rd.java.basic.practice1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Part4Test {
    Part4 calculator;
    @Test
    public void main() {
        assertEquals(5, calculator.getNod(5, 10));
    }
}