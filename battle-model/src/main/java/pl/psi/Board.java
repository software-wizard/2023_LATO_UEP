package pl.psi;

import java.util.ArrayList;
import java.util.*;
import java.util.Optional;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pl.psi.creatures.Creature;

/*
TODO Zdecydowanie wiecej testow na wszystko, sprawdzic wszystko
dodatkowo mozna wygenerowac sobie poprzez wstrzykniecie dodatkowy konstruktor na którym
tworzymy plansze np 5 na 5 i testujemy sobie wszystko, mozna zapiac w debug co sie chce
TODO availablePointsToGo() ma sprawdzac czy dana kreatura moze sie gdzies poruszyc,
w ten sposob  w move nie ma bramki logicznej - sprawdzic w testach i jak trzeba zapiac w debug
*/
public class Board
{
    private static final int MAX_WITDH = 14;
    private List<Point> boardTest = new ArrayList<>();
    private final BiMap< Point, Creature > map = HashBiMap.create();

    public Board( final List< Creature > aCreatures1, final List< Creature > aCreatures2 ) {
        // Tworzymy boarda - wstrzykniecie
        for( int x = 0; x < 15; x++ ){
            for( int y = 0; y < 10; y++ )
            {
                Point point = new Point(x,y);
                boardTest.add(point);
            }
        }
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
    }
    // Mamy stworzone do testow(bylo private ale do testowania robimy wszystko public zeby to sprawdzic)
    @VisibleForTesting
    void addCreatures( final List< Creature > aCreatures, final int aXPosition )
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
        Point startingPoint = getPosition(aCreature);
        Point endPoint = aPoint;
        List<Point> theRightPath = path.findPath(startingPoint, endPoint);
        for (Point point: theRightPath){
            map.inverse().remove( aCreature );
            map.put( aPoint, aCreature );
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
        // Mysle ze problem jest tu taki ze aby cos bylo przypisane do mapy ma miec punkt i kreature
        // a on musi miec plansze po ktorej musi iterowac, na ten moment test przechodzi ale nie wiem jak to docelowo ma wygladac
        for (Point point : boardTest) {
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

    /*
    metoda przyjmuje pkt na ktorym jest, i move range - raczej juz nie przydatne
    mozna wykreowac diagram
    [/] waga jeden dla zwyklego pkt
    [/] dla przeszdody jakas duza liczba
    przeszkoda do przejscia = wieksza waga
    dla algorymu jednostek latajacyhch inny diagram
     */
}
