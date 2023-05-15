package pl.psi;

import WarMachines.WarMachine;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemporaryTurnQueueWithoutCreatures {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String NEXT_WAR_MACHINE = "NEXT_WAR_MACHINE";
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final Collection<WarMachine> warMachines;
    private WarMachine currentWarMachine;
    private int roundNumber;
    private final Queue<WarMachine> warMachinesQueue;


    public TemporaryTurnQueueWithoutCreatures(final Collection<WarMachine> aWarMachineList, final Collection<WarMachine> aWarMachineList2) {
        warMachines = Stream.concat(aWarMachineList.stream(), aWarMachineList2.stream()).collect(Collectors.toList());
        warMachinesQueue = new LinkedList<>();
        initQueue();
        warMachines.forEach(observerSupport::addPropertyChangeListener);
        next();
    }


    private void initQueue() {
        warMachinesQueue.addAll(warMachines);
    }

    public WarMachine getCurrentWarMachine(){
        return currentWarMachine;
    }

    public void next() {
        WarMachine oldWarMachine = currentWarMachine;

        if (warMachinesQueue.isEmpty()) {
            endOfTurn();
        }
        currentWarMachine = warMachinesQueue.poll();
        observerSupport.firePropertyChange(NEXT_WAR_MACHINE, oldWarMachine, currentWarMachine);
    }

    private void endOfTurn() {
        roundNumber++;
        initQueue();
        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
    }

    void addObserver(PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
    }

    public void removeWarMachine(WarMachine warMachine){
        warMachines.remove(warMachine);
    }
}


