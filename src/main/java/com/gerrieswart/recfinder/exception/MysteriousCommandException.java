package com.gerrieswart.recfinder.exception;

/**
 * Thrown when we encountered a command no earthly or martian being can understand
 */
public class MysteriousCommandException extends Exception
{
    public MysteriousCommandException()
    {
    }


    public MysteriousCommandException(String message)
    {
        super(message);
    }
}
