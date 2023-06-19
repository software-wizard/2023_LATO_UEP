package pl.psi.warmachines;

import pl.psi.AttackerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

import java.util.HashMap;

public class Ballista extends WarMachine implements AttackerIF {
    private boolean canBeControlledByPlayer;
    private final int artillerySkillLevel;

    public Ballista(int amount, int artillerySkillLevel) {
        super(WarMachineStatistic.BALLISTA, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        this.artillerySkillLevel = artillerySkillLevel;
        canBeControlledByPlayer = false;
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
        return canBeControlledByPlayer;
    }

    @Override
    public HashMap<String, Integer> getSkill(){
        HashMap<String, Integer> skill = new HashMap<>();
        skill.put("artillery", artillerySkillLevel);
        return skill;
    }

    @Override
    public void setControllable() {
        canBeControlledByPlayer = true;
    }




}
