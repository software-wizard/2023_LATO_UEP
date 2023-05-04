package WarMachines;

import lombok.Getter;

/*
Ammo Cart - one hex size, gives unlimited ammunition for ranged units
 */
public class AmmoCartWarMachine extends WarMachine {
    @Getter
    private final int ammoCartHP = 100;
    @Getter
    private final int ammoCartCost = 1000;
    @Getter
    private final int ammoCartDefence = 5;

    public AmmoCartWarMachine() {

    }

//    public void receiveAttack(Creature attacker){
//        Range<Integer> attackerDamage = attacker.getStats().getDamage();
//        int attackerAttack = attacker.getStats().getAttack();
//        int ammoCartDefence = this.ammoCartDefence;
//
//    }

}
