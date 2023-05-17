package pl.psi;

import WarMachines.MapObjectIf;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewTurnQueuePrototype {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_MAP_OBJECT = "NEXT_MAP_OBJECT";
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final Collection<MapObjectIf> mapObjectIfs;
    private MapObjectIf currentMapObject;
    private int roundNumber;
    private final Queue<MapObjectIf> mapObjectsQueue;


    public NewTurnQueuePrototype(final Collection<MapObjectIf> aMapObjectList, final Collection<MapObjectIf> aMapObjectList2) {
        mapObjectIfs = Stream.concat(aMapObjectList.stream(), aMapObjectList2.stream()).collect(Collectors.toList());
        mapObjectsQueue = new LinkedList<>();
        initQueue();
        mapObjectIfs.forEach(observerSupport::addPropertyChangeListener);
        next();
    }


    private void initQueue() {
        mapObjectsQueue.addAll(mapObjectIfs);
    }

    public MapObjectIf getCurrentMapObject(){
        return currentMapObject;
    }

    public void next() {
        MapObjectIf oldMapObject = currentMapObject;

        if (mapObjectsQueue.isEmpty()) {
            endOfTurn();
        }
        currentMapObject = mapObjectsQueue.poll();
        observerSupport.firePropertyChange(NEXT_MAP_OBJECT, oldMapObject, currentMapObject);
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

    public void removeMapObject(MapObjectIf mapObjectIf){
        mapObjectIfs.remove(mapObjectIf);
    }
}


