package com.gerrieswart.recfinder.util;

import com.gerrieswart.recfinder.Main;
import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * File handling utils for the Rover
 */
public class FileUtil
{
    public static boolean fileExistsAndIsReadable(String fullyQualifiedFileName)
    {
        fullyQualifiedFileName = StringUtil.deNull(fullyQualifiedFileName);
        File f = new File(fullyQualifiedFileName);
        if (!f.exists())
        {
            return false;
        }
        if (!f.canRead())
        {
            return false;
        }
        return true;
    }


    public static Rover initialiseRoverFromConfigFile(String fullyQualifiedFileName, Rover rover)
            throws IOException,
                   ValueAlreadyModifiedException,
                   InvalidZoneBoundsException,
                   OutsideZoneBoundsException,
                   MysteriousCommandException
    {
        Stream<String> lines = Files.lines(Paths.get(fullyQualifiedFileName));

        int lineNumber = 0;
        for (String line : (Iterable<String>) lines::iterator)
        {
            line = StringUtil.deNull(line).trim();
            if (!line.equals("") && line.charAt(0) != '#')
            {
                lineNumber++;
                handleLine(line, lineNumber, rover);
            }
        }
        return rover;
    }


    public static void handleLine(String line, int lineNumber, Rover rover)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException,
                   OutsideZoneBoundsException,
                   MysteriousCommandException
    {
        switch (lineNumber)
        {
            case Main.ZONE_DEFINITION:
                ConfigurationUtil.setZoneBounds(line, rover);
                break;
            case Main.INITIAL_POSITION:
                ConfigurationUtil.setInitialPositionAndHeading(line, rover);
                break;
            case Main.COMMAND_LIST:
                rover.setCommandString(line);
                break;
        }
    }
}
