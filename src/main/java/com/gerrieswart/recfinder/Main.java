package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;
import com.gerrieswart.recfinder.util.FileUtil;

import java.io.IOException;

/**
 * Main entry point for the Rover application.
 */
public class Main
{

    // I use the unix exit codes as defined in sysexits.h
    // It's generally in /usr/include/sysexits.h
    /**
     * Command line usage error. Probably didn't pass the
     * fully qualified filename of the rover config/instruction file.
     */
    public static final int     EX_USAGE         = 64;
    /**
     * Cannot open input. Instruction file doesn't exist or is not
     * readable.
     */
    public static final int     EX_NOINPUT       = 66;
    /**
     * Internal rover error. The rover spacked out, this hopefully
     * never happens.
     */
    public static final int     EX_SOFTWARE      = 70;
    /**
     * Configuration error. The config/instruction file could not
     * be parse 100%.
     */
    public static final int     EX_CONFIG        = 78;
    /**
     * First non-blank uncomment line is the zone
     */
    public static final int     ZONE_DEFINITION  = 1;
    /**
     * Second line is the initial position and heading
     */
    public static final int     INITIAL_POSITION = 2;
    /**
     * Third line is the commands
     */
    public static final int     COMMAND_LIST     = 3;
    /**
     * Should this app be verbose?
     */
    private static      boolean beChatty         = true;


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
        try
        {
            checkIfFilenamePassed(args);
            checkIfVerboseIsSwitchedOff(args);
            printPreamble();
            String filename   = checkIfFileExists(args[0]);
            Rover  spaceBeast = new Rover();
            initialiseRover(filename, spaceBeast);
            startRover(spaceBeast);
            printFinalPosition(spaceBeast);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(EX_SOFTWARE);
        }

    }


    private static void checkIfFilenamePassed(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Usage: java Main ConfigFile<enter>" +
                               "\nwhere ConfigFile is the fully qualified filename of the config file.");
            System.exit(EX_USAGE);
        }
    }


    private static void checkIfVerboseIsSwitchedOff(String[] args)
    {
        if ((args.length == 2) && (args[1].equals("OnlyPrintFinalPos")))
        {
            beChatty = false;
        }
    }


    private static void printPreamble()
    {
        if (beChatty)
        {
            System.out.println("=====================================================");
            System.out.println("==== RectangleFinder. Like PathFinder, only not. ====");
            System.out.println("=====================================================");
            System.out.println(String.format("Working Dir: '%s/'", System.getProperty("user.dir")));
        }
    }


    private static String checkIfFileExists(String arg)
    {
        String filename = arg.trim();
        if (!FileUtil.fileExistsAndIsReadable(filename))
        {
            System.err.println(String.format("File: '%s' does not exist or is not readable.", filename));
            System.exit(EX_NOINPUT);
        }
        return filename;
    }


    private static void initialiseRover(String filename, Rover spaceBeast)
    {
        try
        {
            FileUtil.initialiseRoverFromConfigFile(filename, spaceBeast);
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
    }


    private static void startRover(Rover spaceBeast)
    {
        try
        {
            if (beChatty)
            {
                System.out.println("Rover ready. Into the great wide yonder!");
            }
            spaceBeast.explore();
        }
        catch (OutsideZoneBoundsException fallenIntoTheAbyss)
        {
            System.err.println(fallenIntoTheAbyss.getMessage());
            System.exit(EX_CONFIG);
        }
    }


    private static void printFinalPosition(Rover spaceBeast)
    {
        if (beChatty)
        {
            System.out.println("Rover finished exploring.\n");
            System.out.println("Final position and heading:");
        }
        System.out.println(spaceBeast.getPositionAndHeading());
    }


}
