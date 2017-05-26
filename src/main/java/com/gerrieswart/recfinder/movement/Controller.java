package com.gerrieswart.recfinder.movement;

import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.util.StringUtil;

/**
 * A Movement controller for a rover
 */
public class Controller
{
    Rover              rover;
    TurnLeftCommand    left;
    TurnRightCommand   right;
    MoveForwardCommand move;
    String commands = "";


    public Controller(Rover rover)
    {
        this.rover = rover;
        left = new TurnLeftCommand(rover);
        right = new TurnRightCommand(rover);
        move = new MoveForwardCommand(rover);
    }


    public String getCommands()
    {
        return commands;
    }


    public Controller setCommands(String commands) throws MysteriousCommandException
    {
        commands = StringUtil.deNull(commands);
        commands = StringUtil.removeAllWhitespace(commands);
        commands = commands.toUpperCase();
        validateCommandSequence(commands);
        this.commands = commands;
        return this;
    }


    private void validateCommandSequence(String commands) throws MysteriousCommandException
    {
        if (!commands.matches("[LMR]*"))
        {
            String nonMatchingCommands = commands.replaceAll("[LMR]*", "");
            throw new MysteriousCommandException(
                    String.format("No martian being can understand command string '%s' because the characters '%s' are too mysterious",
                                  commands, nonMatchingCommands));
        }
    }


    /**
     * This rover moves at lightspeed.
     * A real one will have a queue of commands that it executes
     * only once the previous command has completed.
     */
    public Controller executeCommands() throws OutsideZoneBoundsException
    {
        for (char command : commands.toCharArray())
        {
            switch (command)
            {
                case 'L':
                    left.execute();
                    break;
                case 'R':
                    right.execute();
                    break;
                case 'M':
                    move.execute();
                    break;
            }
        }
        return this;
    }

}
