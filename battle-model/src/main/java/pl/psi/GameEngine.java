package pl.psi;

import lombok.Getter;
import pl.psi.creatures.Creature;
import pl.psi.warmachines.WarMachine;

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
    private List<MapObjectIf> mapObjectIf1 = new ArrayList<>();
    private List<MapObjectIf> mapObjectIf2 = new ArrayList<>();
    @Getter
    public Hero hero1;
    @Getter
    public Hero hero2;

    public GameEngine(final Hero aHero1, final Hero aHero2) {
        hero1 = aHero1;
        hero2 = aHero2;

        mapObjectIf1.addAll(aHero1.getCreatures());
        mapObjectIf1.addAll(aHero1.getWarMachines());

        mapObjectIf2.addAll(aHero2.getCreatures());
        mapObjectIf2.addAll(aHero2.getWarMachines());

        setHeroesForLists();

        turnQueue = new TurnQueue(mapObjectIf1, mapObjectIf2);
        board = new Board(mapObjectIf1, mapObjectIf2);
    }

    public void attack(final Point point) {
        if (turnQueue.getCurrentMapObject() instanceof AttackerIF) {
            AttackerIF newAttacker = (AttackerIF) turnQueue.getCurrentMapObject();
            if (newAttacker.canAttack()) {
                board.getMapObject(point)
                        .ifPresent(defender -> {
                            try {
                                AttackerIF attacker = (AttackerIF) turnQueue.getCurrentMapObject();
                                attacker.attack(defender);
                                checkIfAlive(defender);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                pass();
            }
        }
    }

    public void heal(final Point point) {
        if (turnQueue.getCurrentMapObject() instanceof WarMachine) {
            WarMachine newWM = (WarMachine) turnQueue.getCurrentMapObject();
            if (newWM.canHeal()) {
                board.getMapObject(point)
                        .ifPresent(allyUnit -> {
                            try {
                                ((HealerIF) turnQueue.getCurrentMapObject()).heal(allyUnit);
                                checkIfAlive(allyUnit);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                pass();
            }
        }

    }

    public boolean canAttack(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
        boolean canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();

        if (canAttackFromDistance) {
            return board.getMapObject(point)
                    .isPresent()
                    && distance <= 14 && distance > 0
                    && isEnemyUnit(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
        } else {
            return board.getMapObject(point)
                    .isPresent()
                    && distance < 2 && distance > 0;
        }
    }

    public boolean canHeal(final Point point) {
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
        boolean canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();

        if (canAttackFromDistance) {
            return board.getMapObject(point)
                    .isPresent()
                    && distance <= 14 && distance > 0
                    && !isEnemyUnit(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
        } else {
            return board.getMapObject(point)
                    .isPresent()
                    && distance < 2 && distance > 0;
        }
    }

    public boolean isCurrentMapObject(Point aPoint) {
        return Optional.of(turnQueue.getCurrentMapObject()).equals(board.getMapObject(aPoint));
    }

//    public void performAction(final Point point){
//        if()){
//            heal(point);
//        }else {
//            attack(point);
//        }
//    }

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

    public boolean canAttack(final Point point) {
        //if(!turnQueue.getCurrentMapObject().getName().equals("First Aid Tent")){
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
        boolean canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();

        if (canAttackFromDistance) {
            return board.getMapObject(point)
                    .isPresent()
                    && distance <= 14 && distance > 0
                    && isEnemyUnit(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
        } else {
            return board.getMapObject(point)
                    .isPresent()
                    && distance < 2 && distance > 0;
        }
//        }else {
//            return false;
//        }
    }

    public boolean canHeal(final Point point) {
        //if (turnQueue.getCurrentMapObject().getName().equals("First Aid Tent")){
        double distance = board.getPosition(turnQueue.getCurrentMapObject())
                .distance(point);
        boolean canAttackFromDistance = ((AttackerIF) turnQueue.getCurrentMapObject()).canAttackFromDistance();

        if (canAttackFromDistance) {
            return board.getMapObject(point)
                    .isPresent()
                    && distance <= 14 && distance > 0
                    && !isEnemyUnit(turnQueue.getCurrentMapObject(), board.getMapObject(point).get());
        } else {
            return board.getMapObject(point)
                    .isPresent()
                    && distance < 2 && distance > 0;
        }
//        }else {
//            return false;
//        }
    }

    public boolean isCurrentMapObject(Point aPoint) {
        return Optional.of(turnQueue.getCurrentMapObject()).equals(board.getMapObject(aPoint));
    }

    public boolean isEnemyUnit(MapObjectIf mapObject1, MapObjectIf mapObject2) {
//        System.out.println("MO1 hero: " + mapObject1.getHero());
//        System.out.println("MO2 hero: " + mapObject2.getHero());
//        System.out.println(mapObject1.getHero() == mapObject2.getHero());
//        System.out.println("--------------------------------------------");

        if (mapObject1.getHero() != mapObject2.getHero()) {
            return true;
        } else {
            return false;
        }
    }

    public void setHeroesForLists() {
        for (int i = 0; i < mapObjectIf1.size(); i++) {
            mapObjectIf1.get(i).setHero(hero1);
        }
        for (int i = 0; i < mapObjectIf2.size(); i++) {
            mapObjectIf2.get(i).setHero(hero2);
        }
    }

    public void attackRandomEnemy(){
        if (turnQueue.getCurrentMapObject() instanceof WarMachine) {
            WarMachine newWM = (WarMachine) turnQueue.getCurrentMapObject();
            MapObjectIf defender = getRandomMapObject(mapObjectIf1, mapObjectIf2);
            if (newWM.canHeal() && isEnemyUnit(turnQueue.getCurrentMapObject(), defender)) {
                try {
                    AttackerIF attacker = (AttackerIF) turnQueue.getCurrentMapObject();
                    attacker.attack(defender);
                    checkIfAlive(defender);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                pass();
            }
        }
    }

    public void healRandomAlly(){
        if (turnQueue.getCurrentMapObject() instanceof AttackerIF) {
            AttackerIF newAttacker = (AttackerIF) turnQueue.getCurrentMapObject();
            MapObjectIf defender = getRandomMapObject(mapObjectIf1, mapObjectIf2);
            if (newAttacker.canAttack() && isEnemyUnit(turnQueue.getCurrentMapObject(), defender)) {
                try {
                    AttackerIF attacker = (AttackerIF) turnQueue.getCurrentMapObject();
                    attacker.attack(defender);
                    checkIfAlive(defender);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                pass();
            }
        }
    }

//    public boolean isControllable(){
//        if(turnQueue.getCurrentMapObject() instanceof WarMachine){
//            boolean control = ((WarMachine) turnQueue.getCurrentMapObject()).isControllable();
//            System.out.println("This is MO: " + turnQueue.getCurrentMapObject());
//            System.out.println("This is control: " + control);
//            return control;
//        } else if (turnQueue.getCurrentMapObject() instanceof Creature) {
//            boolean control = ((Creature) turnQueue.getCurrentMapObject()).isControllable();
//            System.out.println("This is MO: " + turnQueue.getCurrentMapObject());
//            System.out.println("This is control: " + control);
//            return control;
//        }
//        return false;
//    }

    public MapObjectIf getRandomMapObject(List<MapObjectIf> mapObjectIf1, List<MapObjectIf> mapObjectIf2){
        List<MapObjectIf> mapObjectIf = new ArrayList<MapObjectIf>();
        mapObjectIf.addAll(mapObjectIf1);
        mapObjectIf.addAll(mapObjectIf2);

        Random rand = new Random();
        int i = rand.nextInt(mapObjectIf.size());
        return mapObjectIf.get(i);
    }


}
