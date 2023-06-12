package pl.psi;

//import pl.psi.creatures.Creature;

import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeListener;

public interface MapObjectIf extends PropertyChangeListener {

    int getCurrentHp();

    boolean checkIfAlive(MapObjectIf defender);

    int getMaxHp();

    void setCurrentHp(int max);

    String getName();

    int getMoveRange();

    int getAmount();
    void setAmount(int a);

    boolean canHeal();
    boolean canAttack();
    boolean isControllable();
}

