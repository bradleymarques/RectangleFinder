package com.gerrieswart.recfinder.movement;

import com.gerrieswart.recfinder.Rover;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;

/**
 * Created by gerrie on 2017/05/26.
 */
public class MoveForwardCommand implements Command
{
    Rover rover;


    public MoveForwardCommand(Rover rover)
    {
        this.rover = rover;
    }


    @Override
    public Rover execute() throws OutsideZoneBoundsException
    {
        rover.moveForward();
        return rover;
    }
}
