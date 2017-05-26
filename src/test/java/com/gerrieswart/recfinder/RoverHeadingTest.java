package com.gerrieswart.recfinder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Rover's heading handling
 */
public class RoverHeadingTest
{
    @Test
    public void aNewRoverHeadsNorth() throws Exception
    {
        assertEquals(new Rover().getHeading(), 'N');
    }
}