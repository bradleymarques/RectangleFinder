package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
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


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoWestOfOrigin() throws Exception
    {
        new Rover().setInitialXPosition(-1);
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoSouthOfOrigin() throws Exception
    {
        new Rover().setInitialYPosition(-1);
    }

}