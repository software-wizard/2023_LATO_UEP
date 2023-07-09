// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.warmachines;

import pl.psi.battle.AttackerIF;
import pl.psi.eco.MapObjectIf;
import pl.psi.eco.WarMachines.WarMachineStatistic;

import java.util.HashMap;

public class Catapult extends WarMachine implements AttackerIF {
    public static final String BALLISTICS = "ballistics";
    private final int ballisticSkillLevel;

    public Catapult(int amount, int ballisticSkillLevel) {
        super(WarMachineStatistic.CATAPULT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        this.ballisticSkillLevel = ballisticSkillLevel;
    }

    @Override
    public void attack(MapObjectIf aDefender) throws Exception {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
        }
    }

    @Override
    public boolean canAttackFromDistance() {
        return true;
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    @Override
    public boolean isControllable() {
        return ballisticSkillLevel > 0;
    }

    @Override
    public HashMap<String, Integer> getSkill(){
        HashMap<String, Integer> skill = new HashMap<>();
        skill.put(BALLISTICS, ballisticSkillLevel);
        return skill;
    }

}