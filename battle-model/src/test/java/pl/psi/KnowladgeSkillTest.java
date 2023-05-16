package pl.psi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.KnowladgeSkill;

public class KnowladgeSkillTest {

    @Test
    public void testIncreaseByLevel() {

        KnowladgeSkill knowladgeSkill = new KnowladgeSkill();

        int mana = 0;
        int level = 5;
        int expectedMana = 50;

        int result = knowladgeSkill.increaseByLevel(mana, level);

        Assertions.assertEquals(expectedMana, result, "Incorrect mana value after increasing by level");
    }
}

