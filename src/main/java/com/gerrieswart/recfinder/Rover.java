package com.gerrieswart.recfinder;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    char heading = 'N';


    public char getHeading()
    {
        return heading;
    }


    public Rover turnRight()
    {
        heading = 'E';
        return this;
    }
}
