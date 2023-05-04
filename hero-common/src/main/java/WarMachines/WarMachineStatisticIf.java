package WarMachines;

import com.google.common.collect.Range;

public interface WarMachineStatisticIf {
    String getName();
    int getAttack();
    int getArmor();
    int getMaxHp();
    int getShotRange();
    int getDamage();
    int getTier();
    String getDescription();
    boolean isUpgraded();
}
