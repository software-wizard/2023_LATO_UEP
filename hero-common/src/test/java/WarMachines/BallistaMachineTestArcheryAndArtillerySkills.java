package WarMachines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BallistaMachineTestArcheryAndArtillerySkills {

    @Test
    void calculateDamageFromBallistaWithNoArcherySkill() {
        int heroAttackPoints = 5;
        BallistaMachine ballistaMachine = new BallistaMachine(heroAttackPoints,0,0,0);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertFalse(damage < 2 * (heroAttackPoints + 1));
        assertFalse(damage > 3 * (heroAttackPoints + 1));
    }

    // 50% base dmg boost
    // lowerBoundary = 3 ; upperBoundary = 4.5; but the random dmg number is rounded to the nearest value

    @Test
    void calculateDamageFromBallistaWithArcherySkillLvl3() {
        int heroArcherySkill = 3;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,0,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue(damage >=  Math.round(2*1.5));
        assertTrue(damage <= Math.round(3*1.5));
    }

    // 25% base dmg boost
    // lowerBoundary = 2.5 ; upperBoundary = 3.75; but the random dmg number is rounded to the nearest value

    @Test
    void calculateDamageFromBallistaWithArcherySkillLvl2() {
        int heroArcherySkill = 2;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,0,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue(damage >= Math.round(2*1.25));
        assertTrue(damage <= Math.round(3*1.25));
    }

    // 10% base dmg boost
    // lowerBoundary = 2.2 ; upperBoundary = 3.3; but the random dmg number is rounded to the nearest value

    @Test
    void calculateDamageFromBallistaWithArcherySkillLvl1() {
        int heroArcherySkill = 1;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,0,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue(damage >= Math.round(2*1.1));
        assertTrue(damage <= Math.round(3*1.1));
    }

    @Test
    void calculateDamageFromBallistaWithArcheryLvl1AndArtilleryLvl1() {
        int heroArcherySkill = 1;
        int heroArtillerySkill = 1;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,heroArtillerySkill,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue((damage >= Math.round(2*1.1) && damage <= Math.round(3*1.1)) ||
                damage >= Math.round(2*2*1.1) && damage <= Math.round(3*2*1.1));
    }

    @Test
    void calculateDamageFromBallistaWithArcheryLvl3AndArtilleryLvl1() {
        int heroArcherySkill = 3;
        int heroArtillerySkill = 1;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,heroArtillerySkill,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue((damage >= Math.round(2*1.5) && damage <= Math.round(3*1.5)) ||
                damage >= Math.round(2*2*1.5) && damage <= Math.round(3*2*1.5));
    }

    @Test
    void calculateDamageFromBallistaWithArcheryLvl3AndArtilleryLvl3() {
        int heroArcherySkill = 3;
        int heroArtillerySkill = 3;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,heroArtillerySkill,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue( damage >= Math.round(4*2*1.5) && damage <= Math.round(4*3*1.5));
    }

    @Test
    void calculateDamageFromBallistaWithArcheryLvl3AndArtilleryLvl2() {
        int heroArcherySkill = 3;
        int heroArtillerySkill = 2;
        BallistaMachine ballistaMachine = new BallistaMachine(0,0,heroArtillerySkill,heroArcherySkill);
        int damage = ballistaMachine.getBallistaDamage();
        System.out.println("Damage: " + damage);
        assertTrue( damage >= Math.round(4*2*1.5) && damage <= Math.round(4*3*1.5) ||
                damage >= Math.round(2*2*1.5) && damage <= Math.round(2*3*1.5));
    }

}
