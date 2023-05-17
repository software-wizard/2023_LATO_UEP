package pl.psi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.KnowladgeSkill;

public class KnowladgeSkillTest {

    @Test
    public void testIncreaseByLevel() {

        KnowladgeSkill knowladgeSkill = new KnowladgeSkill();

        int manaMax = 0;
        int level = 5;
        int expectedMana = 50;

        int result = knowladgeSkill.increaseByLevel(manaMax, level);

        Assertions.assertEquals(expectedMana, result, "Incorrect mana value after increasing by level");
    }
}

