package com.gerrieswart.recfinder.util;

/**
 * String (mostly commandstring) utils
 */
public class StringUtil
{

    public static String removeAllWhitespace(String commands)
    {
        commands = deNull(commands);
        return commands.replaceAll("\\s", "");
    }


    public static String deNull(String s)
    {
        return s == null ? "" : s;
    }
}
