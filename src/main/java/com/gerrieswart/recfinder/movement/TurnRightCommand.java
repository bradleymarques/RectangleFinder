package com.gerrieswart.recfinder.movement;

import com.gerrieswart.recfinder.Rover;

/**
 * Created by gerrie on 2017/05/26.
 */
public class TurnRightCommand implements Command
{
    Rover rover;


    public TurnRightCommand(Rover rover)
    {
        this.rover = rover;
    }


    @Override
    public Rover execute()
    {
        rover.turnRight();
        return rover;
    }
}
