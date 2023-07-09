package pl.psi;

import java.util.Collection;
import java.util.Random;

public class RandomMapObject {
    private final Collection<MapObjectIf> mapObjectIfs1;
    private final Collection<MapObjectIf> mapObjectIfs2;

    public RandomMapObject(final Collection<MapObjectIf> aMapObjectList1,
                           final Collection<MapObjectIf> aMapObjectList2) {
        mapObjectIfs1 = aMapObjectList1;
        mapObjectIfs2 = aMapObjectList2;
    }

    public MapObjectIf getRandomMapObject(Collection<MapObjectIf> aMapObjectIfs) {
        Random rand = new Random();
        if (aMapObjectIfs.isEmpty()) {
            return null;
        } else {
            int i = rand.nextInt(aMapObjectIfs.size());
            MapObjectIf randMO = aMapObjectIfs.stream().skip(i).findFirst().orElse(null);
            return randMO;
        }
    }

    public MapObjectIf getRandomMapObject(Collection<MapObjectIf> mapObjectIfs1,
                                          Collection<MapObjectIf> mapObjectIfs2,
                                          MapObjectIf currentMapObject) {
        MapObjectIf mo;
        if (currentMapObject.canAttack()) {

            if (mapObjectIfs1.contains(currentMapObject)) {
                mo = getRandomMapObject(mapObjectIfs2);
            } else {
                mo = getRandomMapObject(mapObjectIfs1);
            }
            System.out.println("CurrMO: " + currentMapObject + " - Random MO for attack: " + mo);
            return mo;

        } else {

            if (mapObjectIfs1.contains(currentMapObject)) {

                do {
                    mo = getRandomMapObject(mapObjectIfs1);
                } while (currentMapObject.equals(mo));

            } else {

                do {
                    mo = getRandomMapObject(mapObjectIfs2);
                } while (currentMapObject.equals(mo));

            }
            System.out.println("CurrMO: " + currentMapObject + " - Random MO for heal: " + mo);
            return mo;

        }
    }


}
