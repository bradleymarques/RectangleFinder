package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;
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
        Rover r = new Rover().setStartingX(1);

        assertEquals(1, r.getX());
        assertEquals(0, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsYPos() throws Exception
    {
        Rover r = new Rover().setStartingY(1);

        assertEquals(0, r.getX());
        assertEquals(1, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsInitialPos() throws Exception
    {
        Rover r = new Rover().setStartingX(1).setStartingY(1);

        assertEquals(1, r.getX());
        assertEquals(1, r.getY());
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoWestOfOrigin() throws Exception
    {
        new Rover().setStartingX(-1);
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoSouthOfOrigin() throws Exception
    {
        new Rover().setStartingY(-1);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverCannotChangeTheStartingXPositionMoreThanOnce() throws Exception
    {
        new Rover()
                .setStartingX(1)
                .setStartingX(1);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverCannotChangeTheStartingYPositionMoreThanOnce() throws Exception
    {
        new Rover()
                .setStartingY(1)
                .setStartingY(1);
    }
}