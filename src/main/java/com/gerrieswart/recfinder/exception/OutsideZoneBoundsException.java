package com.gerrieswart.recfinder.exception;

/**
 * Created by gerrie on 2017/05/26.
 */
public class OutsideZoneBoundsException extends Exception
{
    public OutsideZoneBoundsException()
    {
    }


    public OutsideZoneBoundsException(String message)
    {
        super(message);
    }
}
