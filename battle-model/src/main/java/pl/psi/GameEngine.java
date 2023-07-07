package pl.psi;

import com.google.common.base.Preconditions;
import lombok.Getter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngine {

    public static final String CREATURE_MOVED = "CREATURE_MOVED";
    private final Board board;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(this);
    private final TurnQueue turnQueue;
    @Getter
    protected List<MapObjectIf> mapObjectIf1 = new ArrayList<>();
    protected List<MapObjectIf> mapObjectIf2 = new ArrayList<>();

    public Hero hero1;

    public Hero hero2;

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;

        mapObjectIf1.addAll(aHero1.getCreatures());
        mapObjectIf1.addAll(aHero1.getWarMachines());

        mapObjectIf2.addAll(aHero2.getCreatures());
        mapObjectIf2.addAll(aHero2.getWarMachines());

        turnQueue = new TurnQueue(mapObjectIf1, mapObjectIf2);
        board = new Board(mapObjectIf1, mapObjectIf2);
    }

    public boolean isEnemy(MapObjectIf attacker, MapObjectIf defender){
        if(mapObjectIf1.contains(attacker)){
            if (mapObjectIf1.contains(defender)){
                return false;
            }else {
                return true;
            }
        } else if (mapObjectIf2.contains(attacker)) {
            if (mapObjectIf2.contains(defender)){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public void attack(final Point point) {
        board.getMapObject(point)
                .ifPresent(defender -> {
                    try {
                        Preconditions.checkArgument(turnQueue.getCurrentMapObject().canAttack(), "Current map object is not an attacker");
                        ((AttackerIF) turnQueue.getCurrentMapObject()).attack(defender);
                        checkIfAlive(defender);
                        checkIfAlive(turnQueue.getCurrentMapObject());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    public void heal(final Point point) {
        board.getMapObject(point)
                .ifPresent(allyUnit -> {
                    try {
                        Preconditions.checkArgument(turnQueue.getCurrentMapObject().canHeal(), "Current map object is not a healer");
                        ((HealerIF) turnQueue.getCurrentMapObject()).heal(allyUnit);
                        checkIfAlive(allyUnit);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        pass();
    }

    private void checkIfAlive(MapObjectIf defender) {
        if (!(turnQueue.getCurrentMapObject().checkIfAlive(defender))) {
            board.removeMapObject(defender);
            turnQueue.removeMapObject(defender);
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

    public boolean canHeal(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
            return board.getMapObject(point)
                    .isPresent()
                    && distance > 0
                    && !isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get())
                    //&& board.getMapObject(point).get().getCurrentHp() < board.getMapObject(point).get().getMaxHp()
                    && turnQueue.getCurrentMapObject().canHeal();
        }

    public boolean isCurrentMapObject(Point aPoint) {
        return Optional.of(turnQueue.getCurrentMapObject()).equals(board.getMapObject(aPoint));
    }

    public boolean canPerformAction() {
        return turnQueue.getCurrentMapObject() instanceof ActionPerformerIf;
    }

    // This method verifies if the current map object is an attacker and if it can attack from distance
    public boolean canAttack(final Point point) {
        if (turnQueue.getCurrentMapObject().canAttack()) {
            double distance = board.getPosition(turnQueue.getCurrentMapObject())
                    .distance(point);

//            Can attack from distance?
            boolean canAttackFromDistance;
            Preconditions.checkArgument(turnQueue.getCurrentMapObject().canAttack(), "Current map object is not an attacker");
            canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();

            if (canAttackFromDistance) {
                return board.getMapObject(point)
                        .isPresent()
                        && distance > 0
                        && isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
            } else {
                return board.getMapObject(point)
                        .isPresent()
                        && distance < 2 && distance > 0
                        && isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
            }
        } else {
            return false;
        }
    }

    public Point getMapObjectPosition(MapObjectIf mapObjectIf){
        return board.getPosition(mapObjectIf);
    }

    public MapObjectIf getRandomMapObject(){
        return turnQueue.getRandomMapObject();
    }

//    public MapObjectIf getRandomMapObject(Collection<MapObjectIf> aMapObjectIfs){
//        Random rand = new Random();
//        int i = rand.nextInt(aMapObjectIfs.size());
//        MapObjectIf randMO = aMapObjectIfs.stream().skip(i).findFirst().orElse(null);
//        return randMO;
//    }
//
//    public MapObjectIf getRandomMapObject(){
//        MapObjectIf mo;
//        if(turnQueue.getCurrentMapObject().canAttack()){
//
//            if(mapObjectIf1.contains(turnQueue.getCurrentMapObject())){
//                mo = getRandomMapObject(turnQueue.getMapObjectIfs2());
//            }else {
//                mo = getRandomMapObject(turnQueue.getMapObjectIfs1());
//            }
//            System.out.println("CurrMO: " + turnQueue.getCurrentMapObject() + " - Random MO for attack: " + mo);
//            return mo;
//
//        }else {
//
//            if(mapObjectIf1.contains(turnQueue.getCurrentMapObject())){
//
//                do{
//                    mo = getRandomMapObject(turnQueue.getMapObjectIfs1());
//                }while (turnQueue.getCurrentMapObject().equals(mo));
//
//            }else {
//
//                do{
//                    mo = getRandomMapObject(turnQueue.getMapObjectIfs2());
//                }while (turnQueue.getCurrentMapObject().equals(mo));
//
//            }
//            System.out.println("CurrMO: " + turnQueue.getCurrentMapObject() + " - Random MO for heal: " + mo);
//            return mo;
//
//        }
//    }

    public boolean isControllable() {
        return turnQueue.getCurrentMapObject().isControllable();
    }

    public void verifyControllability() {
        if(!isControllable()) {
            if (turnQueue.isTurnQueueEmpty()) {
                turnQueue.endOfTurn();
            } else {
                pass();
            }
        }
    }

//    public boolean canHeal(final Point point) {
//        if (turnQueue.getCurrentMapObject().canHeal()) {
//            double distance = board.getPosition(turnQueue.getCurrentMapObject())
//                    .distance(point);
//            return board.getMapObject(point)
//                    .isPresent()
//                    && distance <= 14 && distance > 0
//                    && !hero1.isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get())
//        } else {
//            return false;
//        }
//    }


    //WARNING! This method creates conflicts because all MapObjects are not attackers - it throws casting exceptions
//    public boolean canAttack(final Point point) {
//        double distance = board.getPosition(turnQueue.getCurrentMapObject())
//                .distance(point);
//        boolean canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();
//        if (canAttackFromDistance) {
//            return board.getMapObject(point)
//                    .isPresent()
//                    && distance <= 14 && distance > 0
//                    && hero1.isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get())
//                    && turnQueue.getCurrentMapObject().canAttack();
//        } else {
//            return board.getMapObject(point)
//                    .isPresent()
//                    && distance < 2 && distance > 0
//                    && hero1.isEnemy(turnQueue.getCurrentMapObject(), board.getMapObject(point).get())
//                    && turnQueue.getCurrentMapObject().canAttack();
//        }
//    }
}

