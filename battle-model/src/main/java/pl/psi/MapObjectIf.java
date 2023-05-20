package pl.psi;

//import pl.psi.creatures.Creature;

import java.beans.PropertyChangeListener;

public interface MapObjectIf extends PropertyChangeListener {

    int getCurrentHp();
    void attack(MapObjectIf defender) throws Exception;

    void heal(MapObjectIf ally) throws Exception;

    boolean checkIfAlive(MapObjectIf defender);

    int getMaxHp();

    void setCurrentHp(int max);

    String getName();

    int getMoveRange();

    boolean canAttackFromDistance();

    Hero getHero();

    void setHero(Hero hero);
}

