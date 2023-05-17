package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

public class ArmourerSkillTest {
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Mock
    private Random randomMock;

    @Test
    void shouldDecreaseDamageBy10perCent() {

        // Initialize the mocks
        MockitoAnnotations.initMocks(this);

        // Set the fixed number to be returned by the randomMock
        Mockito.when(randomMock.nextInt(Mockito.anyInt())).thenReturn(5);


        Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .armor(0)
                        .build())
                .build();
        Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .armor(0)
                        .build())
                .build();

        ArmourerSkill armourerSkill = new ArmourerSkill(randomMock);

        armourerSkill.apply(attacker);

        attacker.attack(defender);

        Assertions.assertEquals(9.5,defender.getCurrentHp(),"");
    }
}
