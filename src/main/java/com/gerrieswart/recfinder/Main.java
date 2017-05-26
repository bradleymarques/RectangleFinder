package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;
import com.gerrieswart.recfinder.util.ConfigurationUtil;
import com.gerrieswart.recfinder.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Main entry point for the Rover application.
 */
public class Main
{

    /**
     * Exit success.
     */
    public static final int EX_OK      = 0;

    // I use the unix exit codes as defined in sysexits.h
    // It's generally in /usr/include/sysexits.h
    /**
     * Command line usage error. Probably didn't pass the
     * fully qualified filename of the rover config/instruction file.
     */
    public static final int EX_USAGE   = 64;
    /**
     * Cannot open input. Instruction file doesn't exist or is not
     * readable.
     */
    public static final int EX_NOINPUT = 66;
    /**
     * Internal rover error. The rover spacked out, this hopefully
     * never happens.
     */
    public static final int EX_SOFTWARE = 70;
    /**
     * Configuration error. The config/instruction file could not
     * be parse 100%.
     */
    public static final int EX_CONFIG = 78;
    public static final int ZONE_DEFINITION  = 1;
    public static final int INITIAL_POSITION = 2;
    public static final int COMMAND_LIST     = 3;
    private static boolean beChatty = true;


    /**
     * Main entry.
     *
     * @param args [0] the fully qualified filename of the config/instruction file to
     *             run. In theory you can pass relative paths (did test that), that's
     *             why I print the working dir :)
     *             [1] Optional - if you pass OnlyPrintFinalPos as the 2nd argument
     *             it will *only* print errors and the final position of the Rover. That's
     *             boring, though, isn't it?
     */
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Usage: java Main ConfigFile<enter>" +
                               "\nwhere ConfigFile is the fully qualified filename of the config file.");
            System.exit(EX_USAGE);
        }

        if ((args.length == 2) && (args[1].equals("OnlyPrintFinalPos")))
        {
            beChatty = false;
        }

        if (beChatty)
        {
            System.out.println("=====================================================");
            System.out.println("==== RectangleFinder. Like PathFinder, only not. ====");
            System.out.println("=====================================================");
            System.out.println(String.format("Working Dir: '%s/'", System.getProperty("user.dir")));
        }

        String filename = args[0].trim();
        if (!fileExistsAndIsReadable(filename))
        {
            System.err.println(String.format("File: '%s' does not exist or is not readable.", filename));
            System.exit(EX_NOINPUT);
        }

        Rover spaceBeast = new Rover();

        try
        {
            initialiseRoverFromConfigFile(filename, spaceBeast);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(EX_NOINPUT);
        }
        catch (ValueAlreadyModifiedException e)
        {
            System.err.println(e.getMessage());
            System.exit(EX_CONFIG);
        }
        catch (InvalidZoneBoundsException e)
        {
            System.err.println(e.getMessage());
            System.exit(EX_CONFIG);
        }
        catch (OutsideZoneBoundsException e)
        {
            System.err.println(e.getMessage());
            System.exit(EX_CONFIG);
        }
        catch (MysteriousCommandException e)
        {
            System.err.println(e.getMessage());
            System.exit(EX_CONFIG);
        }


        try
        {
            spaceBeast.explore();
        }
        catch (OutsideZoneBoundsException fallenIntoTheAbyss)
        {
            System.err.println(fallenIntoTheAbyss.getMessage());
            System.exit(EX_CONFIG);
        }


        if (beChatty)
        {
            System.out.println("Rover finished exploring.");
            System.out.println("Final position and heading:");
        }
        System.out.println(spaceBeast.getPositionAndHeading());

    }


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
            if (line.charAt(0) != '#' && !line.equals(""))
            {
                lineNumber++;
                handleLine(line, lineNumber, rover);
            }
        }
        return rover;
    }


    private static void handleLine(String line, int lineNumber, Rover rover)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException,
                   OutsideZoneBoundsException,
                   MysteriousCommandException
    {
        switch (lineNumber)
        {
            case ZONE_DEFINITION:
                ConfigurationUtil.setZoneBounds(line, rover);
                break;
            case INITIAL_POSITION:
                ConfigurationUtil.setInitialPositionAndHeading(line, rover);
                break;
            case COMMAND_LIST:
                rover.setCommandString(line);
                break;
        }
    }
}
