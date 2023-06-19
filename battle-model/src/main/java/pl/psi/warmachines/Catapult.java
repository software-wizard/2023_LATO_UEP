package pl.psi.warmachines;

import lombok.Setter;
import pl.psi.AttackerIF;
import pl.psi.ControlIndicator;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

import java.util.HashMap;

public class Catapult extends WarMachine implements AttackerIF {
    @Setter
    private boolean canBeControlledByPlayer;
    private final int ballisticSkillLevel;

    public Catapult(int amount, int ballisticSkillLevel) {
        super(WarMachineStatistic.CATAPULT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        this.ballisticSkillLevel = ballisticSkillLevel;
        canBeControlledByPlayer = false;
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
        return canBeControlledByPlayer;
    }

    public HashMap<String, Integer> getSkill(){
        HashMap<String, Integer> skill = new HashMap<>();
        skill.put("ballistics", ballisticSkillLevel);
        return skill;
    }

    @Override
    public void setControllable() {
        canBeControlledByPlayer = true;
    }
}
