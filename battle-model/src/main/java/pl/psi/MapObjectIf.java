package pl.psi;

//import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;

public interface MapObjectIf extends PropertyChangeListener {
    int getCurrentHp();

    void attack(MapObjectIf defender) throws Exception;

    void heal(MapObjectIf comrade) throws Exception;

    boolean checkIfAlive(MapObjectIf defender);

    int getMaxHp();

    void setCurrentHp(int max);
    String getName();



    int getMoveRange();

    boolean canAttackFromDistance();

//    void attack(final Point point);
//
//    boolean canMove(final Point point);
//
//    boolean canAttack(final Point point);
//    Optional<Creature> getCreature(final Point point);
//
//    boolean isCurrentCreature(Point point);



}

