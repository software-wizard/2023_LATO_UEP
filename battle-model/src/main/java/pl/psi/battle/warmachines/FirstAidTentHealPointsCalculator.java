// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.warmachines;

import pl.psi.eco.WarMachines.WarMachineStatistic;
import pl.psi.eco.MapObjectIf;

public class FirstAidTentHealPointsCalculator implements FirstAidTentIf{

    public int calculateHealPoint(WarMachine aAttacker, MapObjectIf aAlly, int currentHP) throws Exception {

        int hp;
        if (aAttacker.getStats().equals(WarMachineStatistic.FIRST_AID_TENT)){
            //todo real hero first aid skill value must be used
            hp = calculateHealPoints(1, currentHP);
        } else{
            throw new Exception("Only FirstAidTent can heal");
        }
        return hp;
    }

    private int heroFirstAidSkill;
    public int calculateHealPoints(int heroFirstAidSkill, int currentHp){
        switch (heroFirstAidSkill){
            case 0: currentHp += calculateUpperBoundary(25); break;
            case 1: currentHp += calculateUpperBoundary(50); break;
            case 2: currentHp += calculateUpperBoundary(75); break;
            case 3: currentHp += calculateUpperBoundary(100); break;
        }
        return currentHp;
    }
    private int calculateUpperBoundary(int upperBoundary){
        return (int) (Math.random() * upperBoundary) + 1;
    }
}
