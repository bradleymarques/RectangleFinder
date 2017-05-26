package com.gerrieswart.recfinder;

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


    public Rover setInitialXPosition(int x)
    {
        this.x = x;
        return this;
    }


    public Rover setInitialYPosition(int y)
    {
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
