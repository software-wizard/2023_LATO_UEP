package pl.psi;

import WarMachines.WarMachine;
import pl.psi.creatures.Creature;
import pl.psi.creatures.NewHeroPrototype;

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
    private final TemporaryTurnQueueWithoutCreatures temporaryTurnQueueWithoutCreatures;

//    public newGameEnginePrototype(final Hero aHero1, final Hero aHero2) {
//        turnQueue = new TurnQueue(aHero1.getCreatures(), aHero2.getCreatures());
////        newTurnQueueWithWarMachines = new NewTurnQueueWithWarMachines(aHero1.getCreatures(), aHero2.getCreatures(), aHero1.getWarMachines(),aHero2.getWarMachines());
//
////        TODO war machines also should be added to board
//        board = new Board(aHero1.getCreatures(), aHero2.getCreatures());
//    }

    public NewGameEnginePrototype(final NewHeroPrototype aHero1, final NewHeroPrototype aHero2) {
        temporaryTurnQueueWithoutCreatures = new TemporaryTurnQueueWithoutCreatures(aHero1.getWarMachines(), aHero2.getWarMachines());
        board = new NewBoardPrototype(aHero1.getWarMachines(), aHero2.getWarMachines());
    }

    public void attack(final Point point) {
        board.getWarMachine(point)
                .ifPresent(defender -> {
                    try {
                        temporaryTurnQueueWithoutCreatures.getCurrentWarMachine()
                                .attack(defender);
                        checkIfAlive(defender);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    public void heal(final Point point) {
        board.getWarMachine(point)
                .ifPresent(defender -> {
                    try {
                        temporaryTurnQueueWithoutCreatures.getCurrentWarMachine()
                                .attack(defender);
                        checkIfAlive(defender);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    private void checkIfAlive(WarMachine defender) {
        if(!(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine().checkIfAlive(defender))){
            board.removeWarMachine(defender);
            temporaryTurnQueueWithoutCreatures.removeWarMachine(defender);
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

    public Optional<WarMachine> getWarMachine(final Point aPoint) {
        return board.getWarMachine(aPoint);
    }

    public void pass() {
        temporaryTurnQueueWithoutCreatures.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        temporaryTurnQueueWithoutCreatures.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine())
                .distance(point);
        return board.getWarMachine(point)
                .isPresent()
                && distance < 10 && distance > 0;
    }

    public boolean isCurrentWarMachine(Point aPoint) {
        return Optional.of(temporaryTurnQueueWithoutCreatures.getCurrentWarMachine()).equals(board.getWarMachine(aPoint));
    }
}
