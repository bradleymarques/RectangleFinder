package com.gerrieswart.recfinder;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    Heading heading = Heading.NORTH;


    public Heading getHeading()
    {
        return heading;
    }


    public Rover turnRight()
    {
        this.heading = Heading.values()[(this.heading.getIndex() + 1) % 4];
        return this;
    }
}
