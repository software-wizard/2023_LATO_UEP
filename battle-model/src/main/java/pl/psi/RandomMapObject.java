package pl.psi;

import java.util.Collection;
import java.util.Random;

public class RandomMapObject {
    private final Collection<MapObjectIf> mapObjectIfs1;
    private final Collection<MapObjectIf> mapObjectIfs2;
    private MapObjectIf currentMapObejct;
    public RandomMapObject(final Collection<MapObjectIf> aMapObjectList1,
                           final Collection<MapObjectIf> aMapObjectList2){
        mapObjectIfs1 = aMapObjectList1;
        mapObjectIfs2 = aMapObjectList2;
    }
    public MapObjectIf getRandomMapObject(Collection<MapObjectIf> aMapObjectIfs){
        Random rand = new Random();
        int i = rand.nextInt(aMapObjectIfs.size());
        MapObjectIf randMO = aMapObjectIfs.stream().skip(i).findFirst().orElse(null);
        return randMO;
    }

    public MapObjectIf getRandomMapObject(Collection<MapObjectIf> mapObjectIfs1,
                                          Collection<MapObjectIf> mapObjectIfs2,
                                          MapObjectIf currentMapObejct){
        MapObjectIf mo;
        if(currentMapObejct.canAttack()){

            if(mapObjectIfs1.contains(currentMapObejct)){
                mo = getRandomMapObject(mapObjectIfs2);
            }else {
                mo = getRandomMapObject(mapObjectIfs1);
            }
            System.out.println("CurrMO: " + currentMapObejct + " - Random MO for attack: " + mo);
            return mo;

        }else {

            if(mapObjectIfs1.contains(currentMapObejct)){

                do{
                    mo = getRandomMapObject(mapObjectIfs1);
                }while (currentMapObejct.equals(mo));

            }else {

                do{
                    mo = getRandomMapObject(mapObjectIfs2);
                }while (currentMapObejct.equals(mo));

            }
            System.out.println("CurrMO: " + currentMapObejct + " - Random MO for heal: " + mo);
            return mo;

        }
    }


}
