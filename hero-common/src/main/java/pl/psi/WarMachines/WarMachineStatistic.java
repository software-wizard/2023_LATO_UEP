package pl.psi.WarMachines;

import com.google.common.collect.Range;

import lombok.Getter;
import pl.psi.BehaviourDeterminatorIf;

@Getter
public enum WarMachineStatistic implements WarMachineStatisticIf
{
    AMMO_CART("Ammo Cart", 0, 100, 0, 5, Range.closed(0, 0), 1, 1,
            "Useful to have for long battles if you are using range attackers who run out of ammunition. Another subtle use for the ammo cart is that it can take one extra hit away from a powerful chain lightning - it will be destroyed of course.",
            false, new BehaviourDeterminatorIf() {
        @Override
        public boolean canAttack() {
            return false;
        }

        @Override
        public boolean canHeal() {
            return false;
        }
    }), //
    BALLISTA("Ballista", 10, 250, 24, 10, Range.closed(8, 12), 5, 2,
            "Ballista is affected by hero's attack still, and unless your hero has a strong attack AND Artillery secondary skill, ballista will not do much damage, buy 2500 gold worth of creatures instead. If you are going for Artillery secondary skill, keep in mind that ballista only has 250 hit points, and once destroyed, the skill will remain useless for the rest of the battle. That is why heroes should choose Offense, Armourer, etc over Artillery. Note: without Artillery secondary skill, ballista will shoot without your control.",
            false, new BehaviourDeterminatorIf() {
        @Override
        public boolean canAttack() {
            return true;
        }

        @Override
        public boolean canHeal() {
            return false;
        }
    }), //
    FIRST_AID_TENT("First Aid Tent", 0, 75, 0, 0, Range.closed(0, 0), 1, 2,
            "Heals a small amount of hit points to a top creature in one of your stacks every round. The effect is very weak. First Aid skill gives you control over the tent and makes it heal more hit points, but the tent is extremely easy to destroy, rendering your First Aid skill useless for the rest of the battle - see note on Artillery above. The tent, however, can save you losing a few high-level creatures by topping up their hit points every round.",
            false, new BehaviourDeterminatorIf() {
        @Override
        public boolean canAttack() {
            return false;
        }

        @Override
        public boolean canHeal() {
            return true;
        }
    }), //
    CATAPULT("Catapult", 0, 1000, 24, 10, Range.closed(0, 0), 1, 2,
            "Every hero has one all the time. Catapult is affected by Balistics skill, which unlike other war machine skills is very important to learn. Catapult is durable and comes under your control as you deveop Ballistics. If you are under seige and have powerful spells, destroy the enemy catapult so you can keep your walls and archer towers, turning the tides of battle in your favour. Ballistics secondary skills is essential for towns like Fortress who lack strong flyers and shooters, they must break through the drawbridge before enemy shooters have a good go at them.",
            false, new BehaviourDeterminatorIf() {
        @Override
        public boolean canAttack() {
            return true;
        }

        @Override
        public boolean canHeal() {
            return false;
        }
    }); //


    private final String name;

    private final int attack; //counterattack?
    private final int maxHp;
    private final int shotRange;
    private final int armor;
    private final Range<Integer> damage;
    private final int tier;
    private final int hexSize;
    private final String description;
    private final boolean isUpgraded;
    private final BehaviourDeterminatorIf behaviour;
    
    WarMachineStatistic(final String aName, final int aAttack, final int aMaxHp,
                        final int aShotRange, final int aArmor, final Range<Integer> aDamage, final int aTier, final int aHexSize,
                        final String aDescription, final boolean aIsUpgraded, final BehaviourDeterminatorIf aBehaviour )
    {
        name = aName;
        attack = aAttack;
        maxHp = aMaxHp;
        shotRange = aShotRange;
        armor = aArmor;
        damage = aDamage;
        tier = aTier;
        hexSize = aHexSize;
        description = aDescription;
        isUpgraded = aIsUpgraded;
        behaviour = aBehaviour;
    }

    String getTranslatedName()
    {
        return name;
    }

    public boolean canAttack()
    {
        return behaviour.canAttack();
    }

    public boolean canHeal()
    {
        return behaviour.canHeal();
    }
}
