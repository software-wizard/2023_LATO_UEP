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

public class Ballista extends WarMachine implements AttackerIF {
    public static final String ARTILLERY = "artillery";
    private final int artillerySkillLevel;

    public Ballista(int amount, int artillerySkillLevel) {
        super(WarMachineStatistic.BALLISTA, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        this.artillerySkillLevel = artillerySkillLevel;
    }

    @Override
    public void attack(final MapObjectIf aDefender) throws Exception {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
            // defender.applyDamage(damage); todo
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
        return artillerySkillLevel > 0;
    }

    @Override
    public HashMap<String, Integer> getSkill(){
        HashMap<String, Integer> skill = new HashMap<>();
        skill.put(ARTILLERY, artillerySkillLevel);
        return skill;
    }


}