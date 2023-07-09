// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import lombok.Getter;
import pl.psi.battle.creatures.Creature;
import pl.psi.eco.MapObjectIf;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
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
    @Getter
    private List<Creature>  mapObjectIfs1;
    @Getter
    private List<Creature>  mapObjectIfs2;

    private MapObjectIf currentMapObject;
    private final Queue<MapObjectIf> mapObjectsQueue;
    private int roundNumber;

    public TurnQueue(final List<Creature> aMapObjectList1,
                     final List<Creature> aMapObjectList2) {
        mapObjectIfs1 = aMapObjectList1;
        mapObjectIfs2 = aMapObjectList2;
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

        if (mapObjectIfs1.contains(mapObjectIf)){
            mapObjectIfs1.remove(mapObjectIf);
        }else if(mapObjectIfs2.contains(mapObjectIf)){
            mapObjectIfs2.remove(mapObjectIf);
        }

    }

    public boolean isTurnQueueEmpty() {
        return mapObjectsQueue.isEmpty();
    }
}