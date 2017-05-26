package com.gerrieswart.recfinder;

/**
 * Created by gerrie on 2017/05/26.
 */
public enum Heading
{
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    int index;


    Heading(int index)
    {
        this.index = index;
    }


    public static Heading fromChar(char heading)
    {
        switch (heading)
        {
            case 'N':
                return NORTH;
            case 'E':
                return EAST;
            case 'S':
                return SOUTH;
            case 'W':
                return WEST;
            default:
                return NORTH;
        }
    }


    public int getIndex()
    {
        return index;
    }
}
