package pl.psi;

import java.util.List;

public class MovementOfWalkingCreatures implements IMovementOfCreatures {

    @Override
    public List<Point> move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint) {
        ShortestPathAlgorythm path = new ShortestPathAlgorythm();
        List<Point> theRightPath = path.findPath( aGrid, aStartingPoint, aEndingPoint);
        return theRightPath;
    }
}
