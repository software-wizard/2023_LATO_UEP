// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.WarMachines;

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

}
