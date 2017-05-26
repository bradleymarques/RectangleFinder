package com.gerrieswart.recfinder.exception;

/**
 * Created by gerrie on 2017/05/26.
 */
public class ValueAlreadyModifiedException extends Exception
{
    public ValueAlreadyModifiedException()
    {
    }


    public ValueAlreadyModifiedException(String message)
    {
        super(message);
    }
}
