//package pl.psi;
//
//import WarMachines.WarMachine;
//import pl.psi.creatures.Creature;
//
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class NewTurnQueueWithWarMachines {
//
//    public static final String END_OF_TURN = "END_OF_TURN";
//    public static final String NEXT_CREATURE = "NEXT_CREATURE";
//    public static final String NEXT_WAR_MACHINE = "NEXT_WAR_MACHINE";
//    private final Collection<Creature> creatures;
//    private final Queue<Creature> creaturesQueue;
//    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
//    private final Collection<WarMachine> warMachines;
//    private Creature currentCreature;
//    private WarMachine currentWarMachine;
//    private int roundNumber;
//    private Queue<WarMachine> warMachinesQueue;
//
//
//    public NewTurnQueueWithWarMachines(final Collection<Creature> aCreatureList,
//                                       final Collection<Creature> aCreatureList2, final Collection<WarMachine> aWarMachineList, final Collection<WarMachine> aWarMachineList2) {
//        creatures = Stream.concat(aCreatureList.stream(), aCreatureList2.stream())
//                .collect(Collectors.toList());
//        warMachines = Stream.concat(aWarMachineList.stream(), aWarMachineList2.stream()).collect(Collectors.toList());
//        creaturesQueue = new LinkedList<>();
//        warMachinesQueue = new LinkedList<>();
//        initQueue();
//        creatures.forEach(observerSupport::addPropertyChangeListener);
//        warMachines.forEach(observerSupport::addPropertyChangeListener);
//        next();
//    }
//
//
//    private void initQueue() {
//        creaturesQueue.addAll(creatures);
//        warMachinesQueue.addAll(warMachines);
//    }
//
//    public Creature getCurrentCreature() {
//        return currentCreature;
//    }
//
//    public WarMachine getCurrentWarMachine(){
//        return currentWarMachine;
//    }
//
//    public void next() {
//        Creature oldCreature = currentCreature;
//        WarMachine oldWarMachine = currentWarMachine;
//
//        if (creaturesQueue.isEmpty() && warMachinesQueue.isEmpty()) {
//            endOfTurn();
//        }
//        currentCreature = creaturesQueue.poll();
//        currentWarMachine = warMachinesQueue.poll();
//        observerSupport.firePropertyChange(NEXT_CREATURE, oldCreature, currentCreature);
//        observerSupport.firePropertyChange(NEXT_WAR_MACHINE, oldWarMachine, currentWarMachine);
//    }
//
//    private void endOfTurn() {
//        roundNumber++;
//        initQueue();
//        observerSupport.firePropertyChange(END_OF_TURN, roundNumber - 1, roundNumber);
//    }
//
//    void addObserver(PropertyChangeListener aObserver) {
//        observerSupport.addPropertyChangeListener(aObserver);
//    }
//}
//
//
