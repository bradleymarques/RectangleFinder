package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    Heading heading          = Heading.NORTH;
    int     x                = 0;
    boolean xIsPristine      = true;
    int     y                = 0;
    boolean yIsPristine      = true;
    int     zoneWidth        = 0;
    boolean widthIsPristine  = true;
    int     zoneHeight       = 0;
    boolean heightIsPristine = true;


    public Rover()
    {
    }


    public Rover(int explorationZoneWidth, int explorationZoneHeight)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        this.setExplorationZoneWidth(explorationZoneWidth);
        this.setExplorationZoneHeight(explorationZoneHeight);
    }


    public int getExplorationZoneWidth()
    {
        return zoneWidth;
    }


    public Rover setExplorationZoneWidth(int zoneWidth)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        if (zoneWidth < 1)
        {
            throw new InvalidZoneBoundsException(String.format("An exploration zone cannot have a width of %s", zoneWidth));
        }
        if (widthIsPristine)
        {
            this.zoneWidth = zoneWidth;
            widthIsPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("Exploration zone width has already been set");
        }

        return this;
    }


    public int getExplorationZoneHeight()
    {
        return zoneHeight;
    }


    public Rover setExplorationZoneHeight(int zoneHeight)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        if (zoneHeight < 1)
        {
            throw new InvalidZoneBoundsException(String.format("An exploration zone cannot have a height of %s", zoneHeight));
        }
        if (heightIsPristine)
        {
            this.zoneHeight = zoneHeight;
            heightIsPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("Exploration zone height has already been set");
        }

        return this;
    }


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
        checkPositionWithinBounds(x, zoneWidth, 'X');
        if (xIsPristine)
        {
            this.x = x;
            xIsPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("X value already changed - can no longer set initial/starting position");
        }
        return this;
    }


    private void checkPositionWithinBounds(int position, int zoneWidthOrHeight, char axis) throws OutsideZoneBoundsException
    {
        if (position < 0)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's %s position cannot be set to a negative value (%s)", axis, position));
        }
        if (position >= zoneWidthOrHeight)
        {
            throw new OutsideZoneBoundsException(
                    String.format("Rover's %s position (%s) cannot be beyond the exploration zone %s",
                                  axis, position, getExplorationZoneAsClosedInterval()));
        }
    }


    public Rover setStartingY(int y)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        checkPositionWithinBounds(y, zoneHeight, 'Y');
        if (yIsPristine)
        {
            this.y = y;
            yIsPristine = false;
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


    /**
     * @return ISO 31-11 description of the exploration zone's x and y coords
     */
    public String getExplorationZoneAsClosedInterval()
    {
        return String.format("[0,%s], [0,%s]", zoneWidth - 1, zoneHeight - 1);
    }
}
