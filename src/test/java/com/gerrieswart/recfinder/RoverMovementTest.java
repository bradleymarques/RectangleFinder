package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test how the beasty moves
 */
public class RoverMovementTest
{
    @Test(expected = OutsideZoneBoundsException.class)
    public void aDefaultConstructedRoverCannotMove() throws Exception
    {
        new Rover().moveForward();
    }


    @Test
    public void aRoverInAnExplorationZoneCanMoveNorth() throws Exception
    {
        Rover rover = new Rover(2, 2).moveForward();
        assertEquals(0, rover.getX());
        assertEquals(1, rover.getY());
    }


    @Test
    public void aRoverInAnExplorationZoneCanMoveEast() throws Exception
    {
        Rover rover = new Rover(2, 2)
                .turnRight().moveForward();
        assertEquals(1, rover.getX());
        assertEquals(0, rover.getY());
    }


    @Test
    public void aRoverWithAnInitialPositionCanMoveSouth() throws Exception
    {
        Rover rover = new Rover(2, 2).setStartingY(1);
        rover.setStartingHeading(Heading.SOUTH);
        rover.moveForward();
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
    }


    @Test
    public void aRoverWithAnInitialPositionCanMoveWest() throws Exception
    {
        Rover rover = new Rover(2, 2).setStartingX(1);
        rover.setStartingHeading(Heading.WEST);
        rover.moveForward();
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
    }
}