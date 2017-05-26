package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
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
        Rover r = new Rover(2, 2);

        assertEquals(0, r.getX());
        assertEquals(0, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsXPos() throws Exception
    {
        Rover r = new Rover(2, 2).setStartingX(1);

        assertEquals(1, r.getX());
        assertEquals(0, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsYPos() throws Exception
    {
        Rover r = new Rover(2, 2).setStartingY(1);

        assertEquals(0, r.getX());
        assertEquals(1, r.getY());
    }


    @Test
    public void aNewRoverCanChangeItsInitialPos() throws Exception
    {
        Rover r = new Rover(2, 2).setStartingX(1).setStartingY(1);

        assertEquals(1, r.getX());
        assertEquals(1, r.getY());
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoWestOfOrigin() throws Exception
    {
        new Rover(2, 2).setStartingX(-1);
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoSouthOfOrigin() throws Exception
    {
        new Rover(2, 2).setStartingY(-1);
    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoNorthOfExplorationZone() throws Exception
    {
        Rover rover = new Rover(2, 2);
        try
        {
            rover.setStartingY(2);
        }
        catch (OutsideZoneBoundsException fallenOfPlanet)
        {
            System.err.println(fallenOfPlanet.getMessage());
            throw fallenOfPlanet;
        }

    }


    @Test(expected = OutsideZoneBoundsException.class)
    public void aRoverCannotGoEastOfExplorationZone() throws Exception
    {
        Rover rover = new Rover(2, 2);
        rover.setStartingX(2);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverCannotChangeTheStartingXPositionMoreThanOnce() throws Exception
    {
        new Rover(2, 2)
                .setStartingX(1)
                .setStartingX(1);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverCannotChangeTheStartingYPositionMoreThanOnce() throws Exception
    {
        new Rover(2, 2)
                .setStartingY(1)
                .setStartingY(1);
    }


    @Test(expected = InvalidZoneBoundsException.class)
    public void aZoneCannotBeZeroWidth() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneWidth(0);
    }


    @Test(expected = InvalidZoneBoundsException.class)
    public void aZoneCannotBeZeroHeight() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneHeight(0);
    }


    @Test(expected = InvalidZoneBoundsException.class)
    public void aZoneCannotHaveANegativeWidth() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneWidth(-1);
    }


    @Test(expected = InvalidZoneBoundsException.class)
    public void aZoneCannotHaveANegativeHeight() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneHeight(-1);
    }


    @Test
    public void anExplorationZonesWidthCanBeSet() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneWidth(5);
        assertEquals(rover.getExplorationZoneWidth(), 5);
    }


    @Test
    public void anExplorationZonesHeightCanBeSet() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneHeight(4);
        assertEquals(rover.getExplorationZoneHeight(), 4);

    }


    @Test
    public void anExplorationZonesDimensionsCanBeSet() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneHeight(4);
        rover.setExplorationZoneWidth(6);
        assertEquals(rover.getExplorationZoneHeight(), 4);
        assertEquals(rover.getExplorationZoneWidth(), 6);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aZonesHeightCanOnlyBeDefinedOnce() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneHeight(5).setExplorationZoneHeight(5);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aZonesWidthCanOnlyBeDefinedOnce() throws Exception
    {
        Rover rover = new Rover();
        rover.setExplorationZoneWidth(5).setExplorationZoneWidth(5);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverThatMovedCannotSetAStartingX() throws Exception
    {
        new Rover(2, 2).moveForward().setStartingX(1);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverThatMovedCannotSetAStartingY() throws Exception
    {
        new Rover(2, 2).moveForward().setStartingY(1);
    }


}