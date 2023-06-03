package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

public class WarMachineFactory {

    public WarMachine create(WarMachineStatistic aName, int aSkillLevel, final int aAmount )
    {
        switch(aName) {
            case AMMO_CART:
                return new WarMachine.Builder().statistic((WarMachineStatistic.AMMO_CART))
                        .amount( aAmount )
                        .build();

            case BALLISTA:
                return new WarMachine.Builder().statistic((WarMachineStatistic.BALLISTA))
                        .amount( aAmount )
                        .build();

            case CATAPULT:
                return new WarMachine.Builder().statistic((WarMachineStatistic.CATAPULT))
                        .amount( aAmount )
                        .build();

            case FIRST_AID_TENT:
                return new WarMachine.Builder().statistic((WarMachineStatistic.FIRST_AID_TENT))
                    .amount( aAmount )
                    .build();
        }
        throw new IllegalArgumentException( "Cannot recognize Warmachine by name" );
    }
}
