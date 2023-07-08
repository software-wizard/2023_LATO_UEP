// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.gui;

import lombok.Value;

/**
 * TODO:
 */
@Value
public class Point
{
    private final int x;
    private final int y;
    public Point(final int aX, final int aY)
    {
        x = aX;
        y = aY;
    }

    public double distance( Point aPoint )
    {
        return distance( aPoint.getX(), aPoint.getY() );
    }

    public double distance( double px, double py )
    {
        px -= getX();
        py -= getY();
        return Math.sqrt( px * px + py * py );
    }
    public double distance( Point startingPoint, Point goalPoint )
    {
        return Math.abs(startingPoint.getX() - goalPoint.getX()) + Math.abs(startingPoint.getY() - goalPoint.getY());
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
