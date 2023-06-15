package pl.psi;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

import java.util.List;
public class MovementOfFlyingCreatures implements IMovementOfCreatures{
    private Creature creature;
    private BiMap< Point, Creature> map = HashBiMap.create();
    public MovementOfFlyingCreatures(Creature aCreature, BiMap aMap) {
        creature = aCreature;
        map = aMap;
    }
    @Override
    public void move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint) {
        ShortestPathAlgorythm path = new ShortestPathAlgorythm();
        List<Point> theRightPath = path.findPath( aGrid, aStartingPoint, aEndingPoint);
        if (theRightPath != null) {
            for (Point point : theRightPath) {
                map.inverse().remove(creature);
                map.put(point, creature);
            }
        }
    }
}
