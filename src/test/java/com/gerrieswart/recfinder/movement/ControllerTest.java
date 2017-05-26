package com.gerrieswart.recfinder.movement;

import com.gerrieswart.recfinder.Heading;
import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gerrie on 2017/05/26.
 */
public class ControllerTest
{
    @Test
    public void aControllerCanSetCommands() throws Exception
    {
        Controller oldArduino = new Controller(new Rover());
        oldArduino.setCommands("MLR");
        assertEquals(oldArduino.getCommands(), "MLR");
    }


    @Test
    public void aControllerStripsWhitespaceFromCommand() throws Exception
    {
        Controller oldArduino = new Controller(new Rover());
        oldArduino.setCommands("\r\nM\t    L R     ");
        assertEquals(oldArduino.getCommands(), "MLR");
    }


    @Test
    public void aControllerHandlesCommandsCaseInsensitively() throws Exception
    {
        Controller oldArduino = new Controller(new Rover());
        oldArduino.setCommands("\r\nM\t    L RrmmMm     ");
        assertEquals(oldArduino.getCommands(), "MLRRMMMM");
    }


    @Test
    public void aControllerHandlesANullCommandGracefully() throws Exception
    {
        Controller oldArduino = new Controller(new Rover());
        oldArduino.setCommands(null);
        assertEquals(oldArduino.getCommands(), "");
    }


    @Test(expected = MysteriousCommandException.class)
    public void aControllerIdentifiesADodgyCommandString() throws Exception
    {
        Controller oldArduino = new Controller(new Rover());
        try
        {
            oldArduino.setCommands("LLMM1MRXXYLLSR");
        }
        catch (MysteriousCommandException e)
        {
            System.err.println(e.getMessage());
            throw e;
        }
    }


    @Test
    public void aControllerCanMoveTheRoverAroundAGrid() throws Exception
    {
        Rover  rover    = new Rover(5, 5);
        String commands = "MMMM R MMMM R MMMM LLL MMM";
        rover.setCommandString(commands);
        rover.explore();

        assertEquals(rover.getHeading(), Heading.WEST);
        assertEquals(rover.getX(), 1);
        assertEquals(rover.getY(), 0);

    }
}