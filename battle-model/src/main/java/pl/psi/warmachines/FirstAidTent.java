package pl.psi.warmachines;

import lombok.Setter;
import pl.psi.ControlIndicator;
import pl.psi.HealerIF;
import pl.psi.MapObjectIf;
import pl.psi.WarMachines.WarMachineStatistic;

import java.util.HashMap;

public class FirstAidTent extends WarMachine implements HealerIF {
    @Setter
    private boolean canBeControlledByPlayer;
    private final int firstAidSkillLevel;

    public FirstAidTent(int amount, int firstAidSkillLevel) {
        super(WarMachineStatistic.FIRST_AID_TENT, new WarMachineDamageCalculator(), new FirstAidTentHealPointsCalculator(), amount);
        this.firstAidSkillLevel = firstAidSkillLevel;
        canBeControlledByPlayer = false;
    }

    @Override
    public void heal(MapObjectIf ally) throws Exception {
        if (isAlive()) {
            final int hp = getHPcalculator().calculateHealPoint(this, ally, ally.getCurrentHp());
            ally.setCurrentHp(hp);
            if ((ally.getCurrentHp() > ally.getMaxHp())) {
                ally.setCurrentHp(ally.getMaxHp());
            }
        }
    }

    @Override
    public boolean canHeal() {
        return true;
    }

    @Override
    public boolean isControllable() {
        return canBeControlledByPlayer;
    }

    public HashMap<String, Integer> getSkill(){
        HashMap<String, Integer> skill = new HashMap<>();
        skill.put("firstAid", firstAidSkillLevel);
        return skill;
    }

    @Override
    public void setControllable() {
        canBeControlledByPlayer = true;
    }

}
