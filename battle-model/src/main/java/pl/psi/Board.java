package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private List<Point> boardGeneratedListOfPoints = new ArrayList<>();
    private final BiMap<Point, MapObjectIf> map = HashBiMap.create();

    public Board(final List<? extends MapObjectIf> mapObjectIfs1, final List<? extends MapObjectIf> mapObjectIfs2) {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Point point = new Point(x, y);
                boardGeneratedListOfPoints.add(point);
            }
        }

        addMapObject(mapObjectIfs1, 0);
        addMapObject(mapObjectIfs2, MAX_WITDH);
    }

    private void addMapObject(final List<? extends MapObjectIf> mapObjectIfs, final int aXPosition) {
        for (int i = 0; i < mapObjectIfs.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), mapObjectIfs.get(i));
        }
    }

    public void removeMapObject(MapObjectIf mapObjectIf) {
        map.remove(getPosition(mapObjectIf));
    }

    Optional<MapObjectIf> getMapObject(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    public Optional<Object> getCreature(Point point) {
        return getMapObject(point).filter(Creature.class::isInstance).map(Creature.class::cast);
    }

    void move(final MapObjectIf mapObjectIf, final Point aPoint) {
        ShortestPathAlgorythm path = new ShortestPathAlgorythm();
        Point startingPoint = getPosition(mapObjectIf);
        Point endPoint = aPoint;
        List<Point> theRightPath = path.findPath(gridConstruction(availablePointsToGo(mapObjectIf)), startingPoint, endPoint);
        if (theRightPath != null) {
            for (Point point : theRightPath) {
                map.inverse().remove(mapObjectIf);
                map.put(point, mapObjectIf);
            }
        }
    }

    boolean canMove(final MapObjectIf mapObjectIf, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            return false;
        }
        final Point oldPosition = getPosition(mapObjectIf);
        return aPoint.distance(oldPosition, aPoint) <= mapObjectIf.getMoveRange();
    }

    Point getPosition(MapObjectIf mapObjectIf) {
        if ((map.inverse().get(mapObjectIf)) != null) {
            return map.inverse()
                    .get(mapObjectIf);
        } else {
            return new Point(0, 0);
        }
    }
    //TODO:
    // 1. czy nie ma bloku
    // 2. czy lata (inna logika)
    // 3. czy nie zostało rzucone zaklęcie(sciana ognia np)

    public List<Point> availablePointsToGo(MapObjectIf mapObjectIf) {
        List<Point> listOfPoints = new ArrayList<>();
        for (Point point : boardGeneratedListOfPoints) {
            if (canMove(mapObjectIf, point)) {
                listOfPoints.add(point);
            }
        }
        return listOfPoints;
    }

    public int[][] gridConstruction(List<Point> availablePointsToGo) {
        int width = 0;
        int height = 0;
        for (Point point : availablePointsToGo) {
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

        for (int i = 0; i < grid.length; i++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[i][y] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < availablePointsToGo.size(); i++) {
            Point aPoint = availablePointsToGo.get(i);
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
