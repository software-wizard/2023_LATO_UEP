package pl.psi;

import com.google.common.collect.BiMap;
import pl.psi.creatures.Creature;

public class MovementOfTeleportingCreatures implements IMovementOfCreatures{
    private MapObjectIf creature;
    private BiMap< Point, MapObjectIf> map;
    public MovementOfTeleportingCreatures(MapObjectIf aCreature, BiMap aMap) {
        creature = aCreature;
        map = aMap;
    }
    @Override
    public void move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint) {
        map.inverse().remove(creature);
        map.put(aEndingPoint, creature);
    }
}
