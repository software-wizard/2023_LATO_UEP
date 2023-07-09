// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.psi.eco.creatures.CreatureStats;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OffenceSkillTest {

    private static final Range<Integer> NOT_IMPORTANT_DMG = Range.closed(0, 0);

    @Mock
    private Random randomMock;

    @Test
    @Disabled
    void shouldIncreaseDamageBy10perCent() throws Exception {

        // Initialize the mocks
        MockitoAnnotations.initMocks(this);

        // Set the fixed number to be returned by the randomMock
        Mockito.when(randomMock.nextInt(Mockito.anyInt())).thenReturn(5);

        Creature attacker = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();
        Creature defender = new Creature.Builder().statistic(CreatureStats.builder()
                        .maxHp(100)
                        .damage(Range.closed(10, 10))
                        .attack(50)
                        .armor(0)
                        .build())
                .build();

        OffenceSkill offenceSkill = new OffenceSkill(randomMock);

        offenceSkill.apply(attacker);

        attacker.attack(defender);

        int expectedHp = (int) (100 * 1.1 * 5);

        assertThat(defender.getCurrentHp()).isEqualTo(expectedHp);

        // X nalezy obliczyć tj. Kreatura zada np. 10 obrażeń to z offence na poziomie basic powinna zadać 11.

    }
}