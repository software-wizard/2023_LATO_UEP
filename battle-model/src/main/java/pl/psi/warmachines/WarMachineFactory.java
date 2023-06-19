package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

import javax.xml.catalog.Catalog;

public class WarMachineFactory {

    public WarMachine create(WarMachineStatistic aName, int aSkillLevel, final int amount )
    {
        switch(aName) {
            case BALLISTA:
                return new Ballista(amount, aSkillLevel);
            case CATAPULT:
                return new Catapult(amount, aSkillLevel);

            case FIRST_AID_TENT:
                return new FirstAidTent(amount, aSkillLevel);
        }
        throw new IllegalArgumentException( "Cannot recognize WarMachine by name" );
    }
}
