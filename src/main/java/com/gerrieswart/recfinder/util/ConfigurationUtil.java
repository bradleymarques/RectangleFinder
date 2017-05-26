package com.gerrieswart.recfinder.util;

import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;

import java.util.Scanner;

/**
 * A few utils to set the Rover up from the strings
 * in a config file. File handling is not done here.
 */
public class ConfigurationUtil
{
    public static void setZoneBounds(String zoneBounds, Rover rover)
            throws InvalidZoneBoundsException, ValueAlreadyModifiedException
    {
        zoneBounds = StringUtil.deNull(zoneBounds).trim();
        if (!zoneBounds.matches("^\\d+\\s+\\d+$"))
        {
            throw new InvalidZoneBoundsException(
                    String.format("Couldn't understand zone boundary definition \"%s\"", zoneBounds));
        }
        Scanner s = new Scanner(zoneBounds);
        s.useDelimiter("\\D+"); // any non-digit
        int width  = s.nextInt();
        int height = s.nextInt();
        rover.setExplorationZoneWidth(width);
        rover.setExplorationZoneHeight(height);
    }
}
