package pl.psi.hero;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.player.Player;

@Getter
@Setter
@Builder
public class HeroStatistics {

    private int moveRange;
    private int experience;
    private int level;
    private int mana;
    private int maxMana;

}
