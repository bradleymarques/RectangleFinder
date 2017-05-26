package com.gerrieswart.recfinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Ensure that the setting of initial positions make sense
 */
public class RoverPositionTest
{

    @Test
    public void aNewDefaultRoverIsAtOrigin() throws Exception
    {
        Rover r = new Rover();

        assertEquals(0, r.getX());
        assertEquals(0, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsXPos() throws Exception
    {
        Rover r = new Rover().setInitialXPosition(1);

        assertEquals(1, r.getX());
        assertEquals(0, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsYPos() throws Exception
    {
        Rover r = new Rover().setInitialYPosition(1);

        assertEquals(0, r.getX());
        assertEquals(1, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsInitialPos() throws Exception
    {
        Rover r = new Rover().setInitialXPosition(1).setInitialYPosition(1);

        assertEquals(1, r.getX());
        assertEquals(1, r.getY());
    }
}