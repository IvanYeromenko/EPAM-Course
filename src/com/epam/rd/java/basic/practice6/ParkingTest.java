package com.epam.rd.java.basic.practice6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParkingTest {
    private static Parking parking;
    private static final PrintStream STD_OUT = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        parking = new Parking(4);
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.setOut(STD_OUT);
    }

    @Test
    public void arrive_shouldReturnTrueIfPlaceIsAvailable() {
        boolean expected = Parking.arrive(1);
        assertTrue(expected);
    }

    @Test
    public void arrive_shouldReturnTrueIfPlaceIsAvailableOnTheRightSide() {
        Parking.arrive(1);
        boolean expected = Parking.arrive(1);
        assertTrue(expected);
    }

    @Test
    public void arrive_shouldReturnTrueIfPlaceIsAvailableOnTheLeftSide() {
        Parking.arrive(3);
        boolean expected = Parking.arrive(3);
        assertTrue(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arrive_shouldThrowIllegalArgumentExceptionIfIndexGreaterThanCapacity() {
        Parking.arrive(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arrive_shouldThrowIllegalArgumentExceptionIfIndexLessThanZero() {
        Parking.arrive(-1);
    }

    @Test
    public void arrive_shouldReturnFalseIfAllPlacesAreOccupied() {
        Parking.arrive(0);
        Parking.arrive(1);
        Parking.arrive(2);
        Parking.arrive(3);
        boolean expected = Parking.arrive(1);
        assertFalse(expected);
    }

    @Test
    public void depart_shouldReturnTrueIfCarDeparted() {
        Parking.arrive(0);
        boolean expected = parking.depart(0);
        assertTrue(expected);
    }

    @Test
    public void depart_shouldReturnFalseIfCarDidNotDepart() {
        boolean expected = parking.depart(2);
        assertFalse(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depart_shouldThrowIllegalArgumentExceptionIfIndexLessThanZero() {
        parking.depart(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depart_shouldThrowIllegalArgumentExceptionIfIndexGreaterThanCapacity() {
        parking.depart(6);
    }

    @Test
    public void print_shouldPrintInitialParkingState() {
        Parking.print();
        String expected = "0000\n";
        String result = output.toString();
        assertEquals(expected, result);
    }
}