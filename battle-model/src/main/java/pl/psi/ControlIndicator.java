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
        this.heroArtillerySkillLevel = 0;
        this.heroBallisticsSkillLevel = 0;
        this.heroFirstAidSkillLevel = 0;
    }

    public boolean indicateControl(WarMachineStatistic name) {
        switch (name) {
            case FIRST_AID_TENT:
                return heroFirstAidSkillLevel > 0;
            case BALLISTA:
                return heroArtillerySkillLevel > 0;
            case CATAPULT:
                return heroBallisticsSkillLevel > 0;
            case AMMO_CART:
                throw new IllegalArgumentException("Ammo cart cannot be controlled by player");
            default:
                return false;
        }
    }
}
