package com.gerrieswart.recfinder.util;

import com.gerrieswart.recfinder.Heading;
import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
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


    public static void setInitialPositionAndHeading(String positionAndHeading, Rover rover)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        positionAndHeading = StringUtil.deNull(positionAndHeading).trim();
        positionAndHeading = positionAndHeading.toUpperCase();
        if (!positionAndHeading.matches("^\\d+\\s+\\d+\\s+[NESW]$"))
        {
            throw new OutsideZoneBoundsException(
                    String.format("Couldn't understand start position and heading \"%s\"", positionAndHeading));
        }
        String[] params = positionAndHeading.split("\\s+");
        rover.setStartingX(Integer.parseInt(params[0]));
        rover.setStartingY(Integer.parseInt(params[1]));
        rover.setStartingHeading(Heading.fromChar(params[2].charAt(0)));
    }
}
