package pl.psi;

//import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;

public interface MapObjectIf extends PropertyChangeListener {

    int getCurrentHp();

    boolean checkIfAlive(MapObjectIf defender);

    int getMaxHp();

    void setCurrentHp(int max);

    String getName();

    int getMoveRange();

    boolean canHeal();

    boolean canAttack();
}

