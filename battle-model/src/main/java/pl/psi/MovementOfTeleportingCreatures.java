package pl.psi;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.creatures.Creature;

public class MovementOfTeleportingCreatures implements IMovementOfCreatures{
    private Creature creature;
    private BiMap< Point, Creature> map = HashBiMap.create();
    public MovementOfTeleportingCreatures(Creature aCreature, BiMap aMap) {
        creature = aCreature;
        map = aMap;
    }
    @Override
    public void move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint) {
        map.inverse().remove(creature);
        map.put(aEndingPoint, creature);
    }
}
