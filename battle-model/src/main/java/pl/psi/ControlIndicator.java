package pl.psi;

import lombok.Setter;
import pl.psi.WarMachines.WarMachineStatistic;

public class ControlIndicator {

    @Setter
    private int heroArtillerySkillLevel;
    @Setter
    private int heroBallisticsSkillLevel;
    @Setter
    private int heroFirstAidSkillLevel;

    public ControlIndicator() {
        // TODO: In future, these values should be read from hero's skills
        this.heroArtillerySkillLevel = 3;
        this.heroBallisticsSkillLevel = 3;
        this.heroFirstAidSkillLevel = 3;
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
}
