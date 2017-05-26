package com.gerrieswart.recfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Rover's heading handling - left turns
 */
@RunWith(Parameterized.class)
public class LeftTurnsTest
{

    int     amountOfTurns;
    Heading expectedHeading;


    public LeftTurnsTest(int amountOfTurns, Heading expectedHeading)
    {
        System.out.printf("Testing %s left turn(s), expecting: %s\n", amountOfTurns, expectedHeading);
        this.amountOfTurns = amountOfTurns;
        this.expectedHeading = expectedHeading;
    }


    @Parameterized.Parameters
    public static Collection leftTurns()
    {
        System.out.println("Testing left turns");
        return asList(new Object[][]{
                {1, Heading.WEST},
                {2, Heading.SOUTH},
                {3, Heading.EAST},
                {4, Heading.NORTH},
        });
    }


    @Test
    public void testRightTurns()
    {
        Rover r = new Rover();
        for (int i = 0; i < amountOfTurns; i++)
        {
            r.turnLeft();
        }

        assertEquals(expectedHeading, r.getHeading());
    }
}