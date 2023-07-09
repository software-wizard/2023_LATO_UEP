// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.creatures;

import pl.psi.eco.mapElements.Castle;

public class EconomyFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";
    public EconomyCreature create(Castle.FractionType fraction, final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if(fraction== Castle.FractionType.NECROPOLIS){
            if( !aIsUpgraded )
            {
                switch( aTier )
                {
                    case 1:
                        return new EconomyCreature( CreatureStatistic.SKELETON, aAmount, 60 );
                    case 2:
                        return new EconomyCreature( CreatureStatistic.WALKING_DEAD, aAmount, 100 );
                    case 3:
                        return new EconomyCreature( CreatureStatistic.WIGHT, aAmount, 200 );
                    case 4:
                        return new EconomyCreature( CreatureStatistic.VAMPIRE, aAmount, 360 );
                    case 5:
                        return new EconomyCreature( CreatureStatistic.LICH, aAmount, 550 );
                    case 6:
                        return new EconomyCreature( CreatureStatistic.BLACK_KNIGHT, aAmount, 1200 );
                    case 7:
                        return new EconomyCreature( CreatureStatistic.BONE_DRAGON, aAmount, 1800 );
                    default:
                        throw new IllegalArgumentException( EXCEPTION_MESSAGE );
                }
            }
            else
            {
                switch( aTier )
                {
                    case 1:
                        return new EconomyCreature( CreatureStatistic.SKELETON_WARRIOR, aAmount, 70 );
                    case 2:
                        return new EconomyCreature( CreatureStatistic.ZOMBIE, aAmount, 125 );
                    case 3:
                        return new EconomyCreature( CreatureStatistic.WRAITH, aAmount, 230 );
                    case 4:
                        return new EconomyCreature( CreatureStatistic.VAMPIRE_LORD, aAmount, 500 );
                    case 5:
                        return new EconomyCreature( CreatureStatistic.POWER_LICH, aAmount, 600 );
                    case 6:
                        return new EconomyCreature( CreatureStatistic.DREAD_KNIGHT, aAmount, 1500 );
                    case 7:
                        return new EconomyCreature( CreatureStatistic.GHOST_DRAGON, aAmount, 3000 );
                    case 8:
                        return new EconomyCreature( null, aAmount, 3000 );
                    default:
                        throw new IllegalArgumentException( EXCEPTION_MESSAGE );
                }
            }
        } else if (fraction== Castle.FractionType.RAMPART) {
//            if( !aIsUpgraded )
//            {
//                switch( aTier )
//                {
//                    case 1:
//                        return new EconomyCreature( CreatureStatistic.CENTAUR, aAmount, 70 );
//                    case 2:
//                        return new EconomyCreature( CreatureStatistic.DWARF, aAmount, 120 );
//                    case 3:
//                        return new EconomyCreature( CreatureStatistic.WOOD_ELF, aAmount, 200 );
//                    case 4:
//                        return new EconomyCreature( CreatureStatistic.PEGASUS, aAmount, 250 );
//                    case 5:
//                        return new EconomyCreature( CreatureStatistic.DENDROID_GUARD, aAmount, 350 );
//                    case 6:
//                        return new EconomyCreature( CreatureStatistic.UNICORN, aAmount, 850 );
//                    case 7:
//                        return new EconomyCreature( CreatureStatistic.GREEN_DRAGON, aAmount, 2400 );
//                    default:
//                        throw new IllegalArgumentException( EXCEPTION_MESSAGE );
//                }
//            }
//            else
//            {
//                switch( aTier )
//                {
//                    case 1:
//                        return new EconomyCreature( CreatureStatistic.CENTAUR_CAPTAIN, aAmount, 70 );
//                    case 2:
//                        return new EconomyCreature( CreatureStatistic.BATTLE_DWARF, aAmount, 125 );
//                    case 3:
//                        return new EconomyCreature( CreatureStatistic.GRAND_ELF, aAmount, 230 );
//                    case 4:
//                        return new EconomyCreature( CreatureStatistic.SILVER_PEGASUS, aAmount, 500 );
//                    case 5:
//                        return new EconomyCreature( CreatureStatistic.DENDROID_SOLDIER, aAmount, 600 );
//                    case 6:
//                        return new EconomyCreature( CreatureStatistic.WAR_UNICORN, aAmount, 1500 );
//                    case 7:
//                        return new EconomyCreature( CreatureStatistic.GOLD_DRAGON, aAmount, 3000 );
//                    default:
//                        throw new IllegalArgumentException( EXCEPTION_MESSAGE );
//                }
//            }
        }

        return null;
    }
}