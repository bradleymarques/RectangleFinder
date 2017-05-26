package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    Heading heading    = Heading.NORTH;
    int     x          = 0;
    boolean xIsDefault = true;
    int     y          = 0;
    boolean yIsDefault = true;


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


    public Rover setStartingX(int x)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException

    {
        if (x < 0)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's x position cannot be set to a negative value (%s)", x));
        }

        if (xIsDefault)
        {
            this.x = x;
            xIsDefault = false;
        } else
        {
            throw new ValueAlreadyModifiedException("X value already changed - can no longer set initial/starting position");
        }
        return this;
    }


    public Rover setStartingY(int y)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        if (y < 0)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's y position cannot be set to a negative value (%s)", y));
        }
        if (yIsDefault)
        {
            this.y = y;
            yIsDefault = false;
        } else
        {
            throw new ValueAlreadyModifiedException("Y value already changed - can no longer set initial/starting position");
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
