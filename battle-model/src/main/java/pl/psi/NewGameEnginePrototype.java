package pl.psi;

import WarMachines.MapObjectIf;
import WarMachines.WarMachine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class NewGameEnginePrototype {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
//    private final TurnQueue turnQueue;
    private final NewBoardPrototype board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
//    private final NewTurnQueueWithWarMachines newTurnQueueWithWarMachines;
    private final NewTurnQueuePrototype newTurnQueuePrototype;

//    public newGameEnginePrototype(final Hero aHero1, final Hero aHero2) {
//        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
////        newTurnQueueWithWarMachines = new NewTurnQueueWithWarMachines(aHero1.getCreatures(), aHero2.getCreatures(), aHero1.getWarMachines(),aHero2.getWarMachines());
//
////        TODO war machines also should be added to board
//        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
//    }

    public NewGameEnginePrototype(final MapObject mapObject, final MapObject mapObject1) {
        newTurnQueuePrototype = new NewTurnQueuePrototype(mapObject.getMapObjectIf(), mapObject1.getMapObjectIf());
        board = new NewBoardPrototype(mapObject.getMapObjectIf(), mapObject1.getMapObjectIf());
    }

    public void attack(final Point point) {
        board.getMapObject(point)
                .ifPresent(defender -> {
                    try {
                        newTurnQueuePrototype.getCurrentMapObject()
                                .attack(defender);
                        checkIfAlive(defender);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    private void checkIfAlive(MapObjectIf defender) {
        if(!(newTurnQueuePrototype.getCurrentMapObject().checkIfAlive(defender))){
            board.removeMapObject(defender);
            newTurnQueuePrototype.removeMapObject(defender);
            pass();
        }
    }


//    public boolean canMove(final Point aPoint) {
//        return board.canMove(turnQueue.getCurrentCreature(), aPoint);
//    }

//    public void move(final Point aPoint) {
//        board.move(turnQueue.getCurrentCreature(), aPoint);
//        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
//    }

    public Optional<MapObjectIf> getMapObject(final Point aPoint) {
        return board.getMapObject(aPoint);
    }

    public void pass() {
        newTurnQueuePrototype.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        newTurnQueuePrototype.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(newTurnQueuePrototype.getCurrentMapObject())
                .distance(point);
        return board.getMapObject(point)
                .isPresent()
                && distance < 10 && distance > 0;
    }

    public boolean isCurrentMapObject(Point aPoint) {
        return Optional.of(newTurnQueuePrototype.getCurrentMapObject()).equals(board.getMapObject(aPoint));
    }
}
