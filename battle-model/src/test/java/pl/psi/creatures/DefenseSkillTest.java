package pl.psi.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

public class DefenseSkillTest {
    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Mock
    private Random randomMock;

    @Test
    void shouldReduceDamageBy2Point5PercentPerDefenseSkillPoint() {

        // Initialize the mocks
        MockitoAnnotations.initMocks(this);

        // Set the fixed number to be returned by the randomMock
        Mockito.when(randomMock.nextInt(Mockito.anyInt())).thenReturn(5);

        Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(115)
                        .damage(Range.closed(10, 10))
                        .attack(47)
                        .armor(0)
                        .build())
                .build();

        Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(80)
                        .damage(Range.closed(8, 8))
                        .attack(30)
                        .armor(0)
                        .build())
                .build();

        DefenseSkill defenseSkill = new DefenseSkill(randomMock);

        defenseSkill.apply(defender);

        attacker.attack(defender);



    }
}
