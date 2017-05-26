package com.gerrieswart.recfinder.util;

import com.gerrieswart.recfinder.Rover;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gerrie on 2017/05/26.
 */
public class ConfigurationUtilTest
{
    @Test
    public void parserSetsZoneBoundsCorrectly() throws Exception
    {
        Rover rover = new Rover();

        ConfigurationUtil.setZoneBounds("    123   456\t", rover);
        assertEquals(rover.getExplorationZoneWidth(), 123);
        assertEquals(rover.getExplorationZoneHeight(), 456);
    }

}