package pl.psi;

import java.util.ArrayList;
import java.util.*;
import java.util.Optional;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;

/*
TODO availablePointsToGo() ma sprawdzac czy dana kreatura moze sie gdzies poruszyc,
w ten sposob  w move nie ma bramki logicznej - sprawdzic w testach i jak trzeba zapiac w debug
*/
public class Board
{
    private static final int MAX_WITDH = 14;
    private List<Point> boardGeneratedListOfPoints = new ArrayList<>();
    private final BiMap< Point, Creature > map = HashBiMap.create();
    public Board(final List< Creature > aCreatures1, final List< Creature > aCreatures2 ) {
                for( int x = 0; x < 15; x++ ){
            for( int y = 0; y < 10; y++ )
            {
                Point point = new Point(x,y);
                boardGeneratedListOfPoints.add(point);
            }
        }

        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
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
        ShortestPathAlgorythm  path = new ShortestPathAlgorythm();
        Point startingPoint = getPosition(aCreature);
        Point endPoint = aPoint;
        List<Point> theRightPath = path.findPath(gridConstruction(availablePointsToGo(aCreature)), startingPoint, endPoint);
        if (theRightPath != null) {
            for (Point point : theRightPath) {
                System.out.println("YOUR MOM" + point);
                map.inverse().remove(aCreature);
                map.put(point, aCreature);
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
        // Zmieniony dystans w point -> przeciazenie
        return aPoint.distance( oldPosition, aPoint ) <= aCreature.getMoveRange();
    }

    Point getPosition( Creature aCreature )
    {
        return map.inverse()
            .get( aCreature );
    }
    // Musi w przyszłości sprawdzać
    // 1. czy nie ma bloku
    // 2. czy lata (inna logika)
    // 3. czy nie zostało rzucone zaklęcie(sciana ognia np)
    public List<Point> availablePointsToGo(Creature aCretaure) {
    List<Point> listOfPoints = new ArrayList<>();
        for (Point point : boardGeneratedListOfPoints) {
            if (canMove(aCretaure, point)) {
                listOfPoints.add(point); //podmienic 0 na max int
            }
        }
        return listOfPoints;
    }
    public int[][] gridConstruction(List<Point> listOfPoints){
        int width = 0;
        int height = 0;
        for (Point point : listOfPoints) {
            if (point.getX() > width) {
                width = point.getX();
            }
            if (point.getY() > height) {
                height = point.getY();
            }
        }
        width++;
        height++;

        int[][] grid = new int[width][height];
        for (int i = 0; i < listOfPoints.size(); i++) {
            Point aPoint = listOfPoints.get(i);
            grid[aPoint.getX()][aPoint.getY()] = 1;
        }
        return grid;
    }
    /*
    private int[][] transposeGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] transposedGrid = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedGrid[j][i] = grid[i][j];
            }
        }
        return transposedGrid;
    }
     */
}
