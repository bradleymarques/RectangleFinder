package com.gerrieswart.recfinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void aDefaultRoverFacesEastAfter1RightTurn() throws Exception
    {
        assertEquals(new Rover().turnRight().getHeading(), Heading.EAST);
    }


    @Test
    public void aDefaultRoverFacesSouthAfter2RightTurns() throws Exception
    {
        assertEquals(new Rover().turnRight().turnRight().getHeading(), Heading.SOUTH);
    }

    @Test
    public void aDefaultRoverFacesWestAfter3RightTurns() throws Exception
    {
        assertEquals(new Rover().turnRight().turnRight().turnRight().getHeading(), Heading.WEST);
    }

    @Test
    public void aDefaultRoverFacesNorthAfter4RightTurns() throws Exception
    {
        assertEquals(new Rover().turnRight().turnRight().turnRight().turnRight().getHeading(), Heading.NORTH);
    }
}