package pl.psi.WarMachines;

import org.junit.jupiter.api.Test;
import pl.psi.warmachines.FirstAidTentHealPointsCalculator;

import static org.junit.jupiter.api.Assertions.*;

class FirstAidTentHealPointsCalculatorTest {
    FirstAidTentHealPointsCalculator tent = new FirstAidTentHealPointsCalculator();

    @Test
    void calculateHealPointsFirstAidSkillLvlZero(){
        int currentHp = 100;
        int heroFirstAidSkill = 0;
        int HpAfterHeal = tent.calculateHealPoints(heroFirstAidSkill, currentHp);

        assertTrue(HpAfterHeal >= 101 && HpAfterHeal <= 125);
    }
    @Test
    void calculateHealPointsFirstAidSkillLvlOne(){
        int currentHp = 100;
        int heroFirstAidSkill = 1;
        int HpAfterHeal = tent.calculateHealPoints(heroFirstAidSkill, currentHp);

        assertTrue(HpAfterHeal >= 101 && HpAfterHeal <= 150);
    }
    @Test
    void calculateHealPointsFirstAidSkillLvlTwo(){
        int currentHp = 100;
        int heroFirstAidSkill = 2;
        int HpAfterHeal = tent.calculateHealPoints(heroFirstAidSkill, currentHp);

        assertTrue(HpAfterHeal >= 101 && HpAfterHeal <= 175);
    }
    @Test
    void calculateHealPointsFirstAidSkillLvlThree(){
        int currentHp = 100;
        int heroFirstAidSkill = 3;
        int HpAfterHeal = tent.calculateHealPoints(heroFirstAidSkill, currentHp);

        assertTrue(HpAfterHeal >= 101 && HpAfterHeal <= 200);
    }
}