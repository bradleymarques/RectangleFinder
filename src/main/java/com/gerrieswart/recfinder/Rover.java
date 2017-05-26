package com.gerrieswart.recfinder;

import com.gerrieswart.recfinder.exception.InvalidZoneBoundsException;
import com.gerrieswart.recfinder.exception.MysteriousCommandException;
import com.gerrieswart.recfinder.exception.OutsideZoneBoundsException;
import com.gerrieswart.recfinder.exception.ValueAlreadyModifiedException;
import com.gerrieswart.recfinder.movement.Controller;

/**
 * GJS
 * Rover implementation
 */
public class Rover
{
    Heading    heading            = Heading.NORTH;
    boolean    isHeadingPristine  = true;
    int        x                  = 0;
    boolean    xIsPristine        = true;
    int        y                  = 0;
    boolean    yIsPristine        = true;
    int        zoneWidth          = 0;
    boolean    widthIsPristine    = true;
    int        zoneHeight         = 0;
    boolean    heightIsPristine   = true;
    Controller movementController = null;


    public Rover()
    {
        super();
        this.movementController = new Controller(this);
    }


    public Rover(int explorationZoneWidth, int explorationZoneHeight)
            throws InvalidZoneBoundsException,
                   ValueAlreadyModifiedException
    {
        super();
        this.setExplorationZoneWidth(explorationZoneWidth);
        this.setExplorationZoneHeight(explorationZoneHeight);
        this.movementController = new Controller(this);
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
        isHeadingPristine = false;
        return this;
    }


    public Rover turnLeft()
    {
        //three right turns == 1 left
        this.heading = Heading.values()[(this.heading.getIndex() + 3) % 4];
        isHeadingPristine = false;
        return this;
    }


    public Rover setStartingX(int x)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException

    {
        if (xIsPristine)
        {
            this.x = x;
            xIsPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("X value already changed - can no longer set initial/starting position");
        }
        checkXPositionWithinBounds();
        return this;
    }


    private void checkXPositionWithinBounds() throws OutsideZoneBoundsException
    {
        checkPositionWithinBounds(x, zoneWidth, 'X');
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


    /**
     * @return ISO 31-11 description of the exploration zone's x and y coords
     */
    public String getExplorationZoneAsClosedInterval()
    {
        return String.format("[0,%s], [0,%s]", zoneWidth - 1, zoneHeight - 1);
    }


    public Rover setStartingY(int y)
            throws OutsideZoneBoundsException,
                   ValueAlreadyModifiedException
    {

        if (yIsPristine)
        {
            this.y = y;
            yIsPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("Y value already changed - can no longer set initial/starting position");
        }
        checkYPositionWithinBounds();
        return this;
    }


    private void checkYPositionWithinBounds() throws OutsideZoneBoundsException
    {
        checkPositionWithinBounds(y, zoneHeight, 'Y');
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }


    public Rover moveForward() throws OutsideZoneBoundsException
    {
        int[][] deltaArray = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[]   delta      = deltaArray[heading.getIndex()];
        this.x += delta[0];
        this.y += delta[1];
        checkXPositionWithinBounds();
        checkYPositionWithinBounds();
        xIsPristine = false;
        yIsPristine = false;
        return this;
    }


    public Rover setStartingHeading(Heading heading) throws ValueAlreadyModifiedException
    {
        if (isHeadingPristine)
        {
            this.heading = heading;
            isHeadingPristine = false;
        } else
        {
            throw new ValueAlreadyModifiedException("Heading already modified before");
        }
        return this;
    }


    public Rover setCommandString(String commands) throws MysteriousCommandException
    {
        this.movementController.setCommands(commands);
        return this;
    }


    public Rover explore() throws OutsideZoneBoundsException
    {
        this.movementController.executeCommands();
        return this;
    }


    public String getPositionAndHeading()
    {
        return String.format("%s %s %s", x, y, heading.toString().charAt(0));
    }


    public String getCommandString()
    {
        return movementController.getCommands();
    }

}
