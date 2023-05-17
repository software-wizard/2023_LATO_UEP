package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void shouldPlayerHasGold() {
        final Player player = new Player("1", PlayerResources.builder()
                .gold(5)
                .build(), town, heroName, bonus);
        assertEquals(5, player.getResources().getGold());
    }

}
