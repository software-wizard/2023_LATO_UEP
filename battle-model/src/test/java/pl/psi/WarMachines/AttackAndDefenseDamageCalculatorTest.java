package pl.psi.WarMachines;

import WarMachines.WarMachineStatistic;
import org.junit.jupiter.api.Test;
import pl.psi.warmachines.WarMachine;
import static org.junit.jupiter.api.Assertions.*;

public class AttackAndDefenseDamageCalculatorTest {
    WarMachine ballistaAttacker = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build();
    WarMachine ballistaDefender = new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build();

    @Test
    void calculateDamageAttackLvl3DefendersDefenseLvl0() throws Exception {
        /*
        hero's attack = 3
        hero's archery skill = 3
        hero's artillery skill = 0
        defender's defense skill = 0
        ballista's attack = 10+3

        lower and upper base dmg boundaries for Ballista are 2-3
        archery skill = 3, so the new boundaries are 3-4.5
        Ballista's dmg calculation is random(lowerBoundary*(herosAttackSkill+1), upperBoundary*(herosAttackSkill+1))
        random(3*(3+1), 4.5*(3*1)) - so the new boundaries are 12-18
        the difference between Ballista's attack (10 + hero's attack) and the defender's defense skill is 13
        the dmg output has to be increased by 5% for each point (in this case 13*1.05=13.65)
        so the final boundaries are 163.8-245.7
        */

        ballistaAttacker.attack(ballistaDefender);

        int damageOutput = ballistaDefender.getMaxHp()-ballistaDefender.getCurrentHp();

        System.out.println("HP after attacking: "+ ballistaDefender.getCurrentHp());
        System.out.println("Damage output: "+(damageOutput));

        assertTrue(damageOutput>=163);
        assertTrue(damageOutput<=246);

    }
}
