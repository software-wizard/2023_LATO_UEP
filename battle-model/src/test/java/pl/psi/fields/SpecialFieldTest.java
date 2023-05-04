package pl.psi.fields;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.fields.abilities.AbilityStrategy;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecialFieldTest {
    Creature creature = mock(Creature.class);
    Object spell = new Object();
    Object spell2 = new Object();
    SpecialField someBush1 = new SpecialField.Builder().statistics(FieldStats.builder()
                    .maxHp(100)
                    .build())
            .abilities(new AbilityStrategy(creature, spell))
            .build();

    SpecialField someBush2 = new SpecialField.Builder().statistics(FieldStats.builder()
                    .maxHp(100)
                    .build())
            .abilities(new AbilityStrategy(creature, spell), new AbilityStrategy(creature, spell2))
            .build();

    SpecialField someBush3 = new SpecialField.Builder().statistics(FieldStats.builder()
                    .maxHp(100)
                    .build())
            .abilities(new AbilityStrategy(69, creature))
            .build();

    @AfterEach
    void clearLogs(){
        SpecialField.resetLogs();
    }
    @Test
    void specialFieldsShouldBeRemovedIfHpIsZero() {
        someBush1.setHp(0);
        assertThat(someBush1.isAlive()).isFalse();
    }

    @Test
    void demoMagicShouldWork(){
        assertThat(SpecialField.logs.get(0)).isEqualTo("added magic!");
    }

    @Test
    void demoMagicShouldWorkWithMultipleAbilities() {
        assertThat(SpecialField.logs.get(0)).isEqualTo(SpecialField.logs.get(1));
        assertThat(SpecialField.logs.get(1)).isEqualTo(SpecialField.logs.get(2));
    }

    @Test
    void demoDamageShouldBeDealt(){
        assertThat(SpecialField.logs.get(3)).isEqualTo("dealt damage!");
    }


}