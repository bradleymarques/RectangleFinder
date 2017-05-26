package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    Heading heading = Heading.NORTH;
    int     x       = 0;
    int     y       = 0;


    public Heading getHeading()
    {
        return heading;
    }


    public Rover turnRight()
    {
        this.heading = Heading.values()[(this.heading.getIndex() + 1) % 4];
        return this;
    }


    public Rover turnLeft()
    {
        //three right turns == 1 left
        this.heading = Heading.values()[(this.heading.getIndex() + 3) % 4];
        return this;
    }


    public Rover setInitialXPosition(int x) throws OutsideZoneBoundsException
    {
        if (x < 0)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's x position cannot be set to a negative value (%s)", x));
        }
        this.x = x;
        return this;
    }


    public Rover setInitialYPosition(int y) throws OutsideZoneBoundsException
    {
        if (y < 0)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's y position cannot be set to a negative value (%s)", y));
        }
        this.y = y;
        return this;
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }
}
