package pl.psi;

import WarMachines.MapObjectIf;
import pl.psi.warmachines.NewHeroPrototype;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class NewGameEnginePrototype {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final NewBoardPrototype board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final NewTurnQueuePrototype newTurnQueuePrototype;
    private List<MapObjectIf> mapObjectIf1 = null;
    private List<MapObjectIf> mapObjectIf2 = null;

//    public NewGameEnginePrototype(final MapObject mapObject, final MapObject mapObject1) {
//        newTurnQueuePrototype = new NewTurnQueuePrototype(mapObject.getMapObjectIf(), mapObject1.getMapObjectIf());
//        board = new NewBoardPrototype(mapObject.getMapObjectIf(), mapObject1.getMapObjectIf());
//    }
//    public NewGameEnginePrototype(final NewHeroPrototype aHero1, final NewHeroPrototype aHero2) {
//        mapObjectIf1.add((MapObjectIf) aHero1.getCreatures());
//        mapObjectIf1.add((MapObjectIf) aHero1.getWarMachines());
//        mapObjectIf2.add((MapObjectIf) aHero2.getCreatures());
//        mapObjectIf2.add((MapObjectIf) aHero2.getWarMachines());
//
//        newTurnQueuePrototype = new NewTurnQueuePrototype(mapObjectIf1, mapObjectIf2);
//        board = new NewBoardPrototype(mapObjectIf1, mapObjectIf2);
//    }

    public NewGameEnginePrototype(final NewHeroPrototype aHero1, final NewHeroPrototype aHero2) {
        List<MapObjectIf> mapObjectIf1 = new ArrayList<>();
        mapObjectIf1.addAll(aHero1.getCreatures());
        mapObjectIf1.addAll(aHero1.getWarMachines());

        List<MapObjectIf> mapObjectIf2 = new ArrayList<>();
        mapObjectIf2.addAll(aHero2.getCreatures());
        mapObjectIf2.addAll(aHero2.getWarMachines());

        newTurnQueuePrototype = new NewTurnQueuePrototype(mapObjectIf1, mapObjectIf2);
        board = new NewBoardPrototype(mapObjectIf1, mapObjectIf2);
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

    public void heal(final Point point) {

        board.getMapObject(point)
                .ifPresent(comrade -> {
                    try {
                        newTurnQueuePrototype.getCurrentMapObject()
                                .heal(comrade);
                        checkIfAlive(comrade);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    public void performAction(final Point point){
        if(newTurnQueuePrototype.getCurrentMapObject().getName().equals("First Aid Tent")){
            heal(point);
        }else {
            attack(point);
        }
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
//
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
