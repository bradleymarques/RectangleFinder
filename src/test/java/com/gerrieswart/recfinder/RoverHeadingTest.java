package com.gerrieswart.recfinder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Rover's heading handling
 */
public class RoverHeadingTest
{
    @Test
    public void aDefaultRoverHeadsNorth() throws Exception
    {
        assertEquals(new Rover().getHeading(), Heading.NORTH);
    }


    @Test
    public void aDefaultRoverFacesEastAfterARightTurn() throws Exception
    {
        assertEquals(new Rover().turnRight().getHeading(), Heading.EAST);
    }

}