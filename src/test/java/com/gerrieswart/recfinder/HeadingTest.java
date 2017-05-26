package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gerrie on 2017/05/26.
 */
public class HeadingTest
{
    @Test
    public void aRoverCanSetItsInitialHeading() throws Exception
    {
        Rover rover = new Rover().setStartingHeading(Heading.SOUTH);
        assertEquals(rover.getHeading(), Heading.SOUTH);

    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverCanSetItsInitialHeadingOnlyOnce() throws Exception
    {
        new Rover().setStartingHeading(Heading.SOUTH).setStartingHeading(Heading.EAST);
    }


    @Test(expected = ValueAlreadyModifiedException.class)
    public void aRoverThatHasTurnedCannotSetAnInitialHeading() throws Exception
    {
        new Rover().turnRight().setStartingHeading(Heading.EAST);
    }
}
