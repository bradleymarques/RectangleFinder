package com.gerrieswart.recfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Rover's heading handling - right turns
 */
@RunWith(Parameterized.class)
public class RightTurnsTest
{

    int     amountOfTurns;
    Heading expectedHeading;


    public RightTurnsTest(int amountOfTurns, Heading expectedHeading)
    {
        System.out.printf("Testing %s right turn(s), expecting: %s\n", amountOfTurns, expectedHeading);
        this.amountOfTurns = amountOfTurns;
        this.expectedHeading = expectedHeading;
    }


    @Parameterized.Parameters
    public static Collection rightTurns()
    {
        System.out.println("Testing right turns");
        return asList(new Object[][]{
                {1, Heading.EAST},
                {2, Heading.SOUTH},
                {3, Heading.WEST},
                {4, Heading.NORTH},
                });
    }


    @Test
    public void testRightTurns()
    {
        Rover r = new Rover();
        for (int i = 0; i < amountOfTurns; i++)
        {
            r.turnRight();
        }

        assertEquals(expectedHeading, r.getHeading());
    }
}