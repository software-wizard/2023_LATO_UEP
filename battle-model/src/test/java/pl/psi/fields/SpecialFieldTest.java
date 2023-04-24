package pl.psi.fields;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureStats;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpecialFieldTest {

    SpecialField someBushIdk = new SpecialField.Builder().statistics(CreatureStats.builder()
                    .maxHp(100)
                    .damage(null)
                    .attack(0)
                    .armor(0)
                    .build())
            .build();

    @Test
    void specialFieldShouldBeNotNull(){
        assertThat(someBushIdk).isNotNull();
    }

    @Test
    void specialFieldsShouldBeRemovedIfHpIsZero(){
        someBushIdk.setHp(0);
        assertThat(someBushIdk.isAlive()).isFalse();
    }

}