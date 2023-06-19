package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.warmachines.WarMachine;
import pl.psi.warmachines.WarMachineFactory;

import static org.junit.jupiter.api.Assertions.*;

class ControlIndicatorTest {

    @Test
    void indicateControl() {
        ControlIndicator controlIndicator = new ControlIndicator();
        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.BALLISTA));
        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.CATAPULT));
        assertTrue(controlIndicator.indicateControl(WarMachineStatistic.FIRST_AID_TENT));

        controlIndicator.setHeroBallisticsSkillLevel(0);
        controlIndicator.setHeroArtillerySkillLevel(0);
        controlIndicator.setHeroFirstAidSkillLevel(0);

        assertFalse(controlIndicator.indicateControl(WarMachineStatistic.BALLISTA));
        assertFalse(controlIndicator.indicateControl(WarMachineStatistic.CATAPULT));
        assertFalse(controlIndicator.indicateControl(WarMachineStatistic.FIRST_AID_TENT));
    }

    @Test
    void isControllable() {
        WarMachine catapult = new WarMachineFactory().create(WarMachineStatistic.CATAPULT, 0, 1);
        WarMachine ballista = new WarMachineFactory().create(WarMachineStatistic.BALLISTA, 0, 1);
        WarMachine firstAidTent = new WarMachineFactory().create(WarMachineStatistic.FIRST_AID_TENT, 0, 1);
        assertTrue(catapult.isControllable());
        assertTrue(ballista.isControllable());
        assertTrue(firstAidTent.isControllable());
    }
}