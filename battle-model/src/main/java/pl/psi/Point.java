package pl.psi;

import lombok.Value;

/**
 * TODO:
 */
@Value
public class Point
{
    private final int x;
    private final int y;


    public Point( final int aX, final int aY)
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
}
