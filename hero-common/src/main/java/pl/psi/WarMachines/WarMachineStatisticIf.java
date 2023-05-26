package pl.psi.WarMachines;

import com.google.common.collect.Range;

public interface WarMachineStatisticIf {
    String getName();
    int getAttack();
    int getArmor();
    int getMaxHp();
    int getShotRange();
    Range< Integer > getDamage();
    int getTier();
    String getDescription();
    boolean isUpgraded();
    int getHexSize();

    boolean canAttack();

    boolean canHeal();
}
