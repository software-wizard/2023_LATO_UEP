package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;

import javax.xml.catalog.Catalog;

public class WarMachineFactory {

    public WarMachine create(WarMachineStatistic aName, int aSkillLevel, final int amount )
    {
        switch(aName) {
            case AMMO_CART:
                return new AmmoCart(amount);

            case BALLISTA:
                return new Ballista(amount);
            case CATAPULT:
                return new Catapult(amount);

            case FIRST_AID_TENT:
                return new FirstAidTent(amount);
        }
        throw new IllegalArgumentException( "Cannot recognize Warmachine by name" );
    }
}
