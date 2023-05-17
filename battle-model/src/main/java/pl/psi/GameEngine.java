package pl.psi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final TurnQueue turnQueue;
    private List<MapObjectIf> mapObjectIf1 = null;
    private List<MapObjectIf> mapObjectIf2 = null;

    public  GameEngine(final Hero aHero1, final Hero aHero2) {
        List<MapObjectIf> mapObjectIf1 = new ArrayList<>();
        mapObjectIf1.addAll(aHero1.getCreatures());
        mapObjectIf1.addAll(aHero1.getWarMachines());

        List<MapObjectIf> mapObjectIf2 = new ArrayList<>();
        mapObjectIf2.addAll(aHero2.getCreatures());
        mapObjectIf2.addAll(aHero2.getWarMachines());

        turnQueue = new TurnQueue(mapObjectIf1, mapObjectIf2);
        board = new Board(mapObjectIf1, mapObjectIf2);
    }

    public void attack(final Point point) {
        board.getMapObject(point)
                .ifPresent(defender -> {
                    try {
                        turnQueue.getCurrentMapObject()
                                .attack(defender);
                        checkIfAlive(defender);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    public void heal(final Point point) {

        board.getMapObject(point)
                .ifPresent(comrade -> {
                    try {
                        turnQueue.getCurrentMapObject()
                                .heal(comrade);
                        checkIfAlive(comrade);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    public void performAction(final Point point){
        if(turnQueue.getCurrentMapObject().getName().equals("First Aid Tent")){
            heal(point);
        }else {
            attack(point);
        }
    }

    private void checkIfAlive(MapObjectIf defender) {
        if(!(turnQueue.getCurrentMapObject().checkIfAlive(defender))){
            board.removeMapObject(defender);
            turnQueue.removeMapObject(defender);
            pass();
        }
    }

    public boolean canMove(final Point aPoint) {
        return board.canMove(turnQueue.getCurrentMapObject(), aPoint);
    }

    public void move(final Point aPoint) {
        board.move(turnQueue.getCurrentMapObject(), aPoint);
        observerSupport.firePropertyChange(CREATURE_MOVED, null, aPoint);
    }

    public Optional<MapObjectIf> getMapObject(final Point aPoint) {
        return board.getMapObject(aPoint);
    }

    public void pass() {
        turnQueue.next();
    }

    public void addObserver(final PropertyChangeListener aObserver) {
        observerSupport.addPropertyChangeListener(aObserver);
        turnQueue.addObserver(aObserver);
    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
        boolean canAttackFromDistance = turnQueue.getCurrentMapObject().canAttackFromDistance();
        if(canAttackFromDistance){
            return board.getMapObject(point)
                    .isPresent()
                    && distance <= 14 && distance > 0;
        } else {
            return board.getMapObject(point)
                    .isPresent()
                    && distance < 2 && distance > 0;
        }

    }

    public boolean isCurrentMapObject(Point aPoint) {
        return Optional.of(turnQueue.getCurrentMapObject()).equals(board.getMapObject(aPoint));
    }
}
