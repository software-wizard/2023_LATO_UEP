package WarMachines;

//import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;
import java.util.Optional;

public interface MapObjectIf extends PropertyChangeListener {
    int getCurrentHp();

    void attack(MapObjectIf defender) throws Exception;

    void heal(MapObjectIf comrade) throws Exception;

    boolean checkIfAlive(MapObjectIf defender);

    int getMaxHp();

    void setCurrentHp(int max);
    String getName();



    int getMoveRange();

//    void attack(final Point point);
//
//    boolean canMove(final Point point);
//
//    boolean canAttack(final Point point);
//    Optional<Creature> getCreature(final Point point);
//
//    boolean isCurrentCreature(Point point);



}

