package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class TurnQueue {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_MAP_OBJECT = "NEXT_CREATURE";
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final Collection<MapObjectIf> mapObjectIfs;
    private MapObjectIf currentMapObject;
    private final Queue<MapObjectIf> mapObjectsQueue;
    private int roundNumber;

    public TurnQueue(final Collection<MapObjectIf> aMapObjectList1,
                     final Collection<MapObjectIf> aMapObjectList2) {
        mapObjectIfs = Stream.concat(aMapObjectList1.stream(), aMapObjectList2.stream())
                .collect(Collectors.toList());
        mapObjectsQueue = new LinkedList<>();
        initQueue();
        mapObjectIfs.forEach(observerSupport::addPropertyChangeListener);
        next();
    }

    private void initQueue() {
        mapObjectsQueue.addAll(mapObjectIfs);
    }

    public MapObjectIf getCurrentMapObject() {
        return currentMapObject;
    }

    public MapObjectIf getRandomMapObject() {
        MapObjectIf[] array = mapObjectIfs.toArray(new MapObjectIf[0]);
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        return array[randomIndex];
    }

    public void next() {
        MapObjectIf oldMapObject = currentMapObject;

        if (mapObjectsQueue.isEmpty()) {
            endOfTurn();
        }
        currentMapObject = mapObjectsQueue.poll();
        observerSupport.firePropertyChange(NEXT_MAP_OBJECT, oldMapObject, currentMapObject);
    }

    public void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

    public void removeMapObject(MapObjectIf mapObjectIf){
        mapObjectIfs.remove(mapObjectIf);
        mapObjectsQueue.remove(mapObjectIf);
    }

    public boolean isTurnQueueEmpty() {
        return mapObjectsQueue.isEmpty();
    }
}
