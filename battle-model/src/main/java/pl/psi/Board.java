package pl.psi;

import java.util.ArrayList;
import java.util.*;
import java.util.Optional;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board
{
    private static final int MAX_WITDH = 14;
    private final BiMap< Point, Creature > map = HashBiMap.create();

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2 )
    {
        addCreatures( aCreatures1, 0 );
        addCreatures( aCreatures2, MAX_WITDH );
    }

    private void addCreatures( final List< Creature > aCreatures, final int aXPosition )
    {
        for( int i = 0; i < aCreatures.size(); i++ )
        {
            map.put( new Point( aXPosition, i * 2 + 1), aCreatures.get( i ) );
        }
    }

    Optional< Creature > getCreature( final Point aPoint )
    {
        return Optional.ofNullable( map.get( aPoint ) );
    }

    void move( final Creature aCreature, final Point aPoint )
    {
        ShortestPathAlgorythm  path = new ShortestPathAlgorythm(gridConstruction(availablePointsToGo(aCreature)));
        int startX = getPosition(aCreature).getX();
        int startY = getPosition(aCreature).getY();
        int endX = aPoint.getX();
        int endY = aPoint.getY();
        List<Point> theRightPath = path.findPath(startX, startY, endX, endY);
        for (Point point: theRightPath){
            if( canMove( aCreature, aPoint ) )
            {
                map.inverse()
                        .remove( aCreature );
                map.put( aPoint, aCreature );
            }
        }
    }

    boolean canMove( final Creature aCreature, final Point aPoint )
    {
        if( map.containsKey( aPoint ) )
        {
            return false;
        }
        final Point oldPosition = getPosition( aCreature );
        return aPoint.distance( oldPosition.getX(), oldPosition.getY() ) < aCreature.getMoveRange();
    }

    Point getPosition( Creature aCreature )
    {
        return map.inverse()
            .get( aCreature );
    }
    public List<Point> availablePointsToGo(Creature aCretaure) {
        List<Point> listOfPoints = new ArrayList<>();
        for (Point point : map.keySet()) {
            if (canMove(aCretaure, point)) {
                listOfPoints.add(point);
            }
        }
        return listOfPoints;
    }

    public int[][] gridConstruction(List<Point> listOfPoints){
        int a = 0;
        int b = 0;
        for(Point point: listOfPoints){
            if(point.getX() > a) {
                a = point.getX();
                if (point.getY() > b) {
                    b = point.getY();
                }
            }
        }
        int[][] grid = new int[a][b];
        for (Point point: listOfPoints) {
            // na ten moment jeden - pozniej zmiana kosztow
            grid[point.getX()][point.getY()] = 1;
        }
        for (int x = 0; x<=grid.length; x++){
            for (int y = 0; y<=grid[0].length; y++){
                if (grid[x][y] != 1){
                    grid[x][y] = 1000000;
                }
            }
        }
        return grid;
    }

    // metoda przyjmuje pkt na ktorym jest, i move range??
    // mozna wykreowac diagram
    // [/] waga jeden dla zwyklego pkt
    // [/] dla przeszdody jakas duza liczba
    // przeszkoda do przejscia = wieksza waga
    // dla algorymu jednostek latajacyhch inny diagram
}
