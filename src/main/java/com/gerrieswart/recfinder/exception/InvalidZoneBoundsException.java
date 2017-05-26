package com.gerrieswart.recfinder.exception;

/**
 * Thrown when you try to create negative-sized exploration zones
 */
public class InvalidZoneBoundsException extends Exception
{
    public InvalidZoneBoundsException()
    {
    }


    public InvalidZoneBoundsException(String message)
    {
        super(message);
    }
}
