package pl.psi.hero;

import lombok.Builder;
import lombok.Getter;
import pl.psi.player.Player;

@Getter
@Builder
public class HeroStatistics {

    private Player player;
    private int moveRange;

}
