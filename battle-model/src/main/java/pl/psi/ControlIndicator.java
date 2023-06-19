package pl.psi;

import pl.psi.WarMachines.WarMachineStatistic;

import java.util.HashMap;

public class ControlIndicator {

    private int heroArtillerySkillLevel;
    private int heroBallisticsSkillLevel;
    private int heroFirstAidSkillLevel;

    public ControlIndicator(HashMap<String, Integer> skills) {
        updateSkills(skills);
    }

    public boolean indicateControl(WarMachineStatistic name) {
        switch (name) {
            case FIRST_AID_TENT:
                return heroFirstAidSkillLevel > 0;
            case BALLISTA:
                return heroArtillerySkillLevel > 0;
            case CATAPULT:
                return heroBallisticsSkillLevel > 0;
            default:
                return false;
        }
    }

    private void updateSkills(HashMap<String, Integer> skills) {
        if (skills.get("artillery") != null) {
            this.heroArtillerySkillLevel = skills.get("artillery");
        }
        if (skills.get("ballistics") != null) {
            this.heroBallisticsSkillLevel = skills.get("ballistics");
        }
        if (skills.get("firstAid") != null){
            this.heroFirstAidSkillLevel =  skills.get("firstAid");
        }
    }
}
