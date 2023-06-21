package pl.psi.creatures;

public class RampartFactory {

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";

    public Creature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.CENTAUR )
                            .amount( aAmount )
                            .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.DWARF )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.WOOD_ELF )
                            .amount( aAmount )
                            .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.PEGASUS )
                            .amount( aAmount )
                            .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.DENDROID_GUARD )
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.UNICORN )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.GREEN_DRAGON )
                            .amount( aAmount )
                            .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.CENTAUR_CAPTAIN )
                            .amount( aAmount )
                            .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.BATTLE_DWARF )
                            .amount( aAmount )
                            .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.GRAND_ELF )
                            .amount( aAmount )
                            .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.SILVER_PEGASUS)
                            .amount( aAmount )
                            .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.DENDROID_SOLDIER)
                            .amount( aAmount )
                            .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.WAR_UNICORN )
                            .amount( aAmount )
                            .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.GOLD_DRAGON )
                            .amount( aAmount )
                            .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
