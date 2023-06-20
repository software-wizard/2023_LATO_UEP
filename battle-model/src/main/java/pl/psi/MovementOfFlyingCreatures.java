package pl.psi;
import com.google.common.collect.BiMap;
import pl.psi.creatures.Creature;
public class MovementOfFlyingCreatures implements IMovementOfCreatures{
    private Creature creature;
    private BiMap< Point, Creature> map;
    public MovementOfFlyingCreatures(Creature aCreature, BiMap aMap) {
        creature = aCreature;
        map = aMap;
    }
    @Override
    public void move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint) {
        map.inverse().remove(creature);
        map.put(aEndingPoint, creature);
    }
}
