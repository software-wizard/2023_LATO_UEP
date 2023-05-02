package WarMachines;

import com.google.common.collect.Range;

import lombok.Getter;

@Getter
public enum WarMachineStatistic implements WarMachineStatisticIf
{
    AMMO_CART( "Ammo Cart", 0, 5, 100, 0, Range.closed( 0, 0 ), 1,
            "",
            false ), //
    BALLISTA( "Ballista", 10, 10, 250, 0, Range.closed( 2, 3 ), 5,
            "", false ), //
    FIRST_AID_TENT( "First Aid Tent", 0, 0, 75, 0, Range.closed( 0, 0 ), 1,
            "",
            false ), //
    CATAPULT( "Catapult", 10, 10, 1000, 0, Range.closed( 0, 0 ), 1,
            "",
            false ); //


    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int shotRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;

    WarMachineStatistic( final String aName, final int aAttack, final int aArmor, final int aMaxHp,
                         final int aShotRange, final Range< Integer > aDamage, final int aTier, final String aDescription,
                         final boolean aIsUpgraded )
    {
        name = aName;
        attack = aAttack;
        armor = aArmor;
        maxHp = aMaxHp;
        shotRange = aShotRange;
        damage = aDamage;
        tier = aTier;
        description = aDescription;
        isUpgraded = aIsUpgraded;
    }

    String getTranslatedName()
    {
        return name;
    }
}
