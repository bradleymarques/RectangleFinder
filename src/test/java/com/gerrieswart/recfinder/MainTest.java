package com.gerrieswart.recfinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gerrie on 2017/05/26.
 */
public class MainTest
{
    @Test
    public void nullFilenameIsNotCatastrophic() throws Exception
    {
        assertEquals(Main.fileExistsAndIsReadable(null), false);
    }


    @Test
    public void nonExistantFileIsNotReadable() throws Exception
    {
        assertEquals(Main.fileExistsAndIsReadable("bnep"), false);
    }


    @Test
    public void existingReadableFileIsOK() throws Exception
    {
        assertEquals(Main.fileExistsAndIsReadable("src/test/resources/basic_test.rover"),
                     true);
    }


    @Test
    public void handlesBlanksAndCommentsOK() throws Exception
    {
        Rover rover = new Rover();
        Main.initialiseRoverFromConfigFile("src/test/resources/basic_test.rover", rover);
        assertEquals(rover.getExplorationZoneAsClosedInterval(), "[0,7], [0,7]");
        assertEquals(rover.getX(), 1);
        assertEquals(rover.getY(), 2);
        assertEquals(rover.getHeading(), Heading.EAST);
        assertEquals(rover.getCommandString(), "MMLMRMMRRMML");
    }

}