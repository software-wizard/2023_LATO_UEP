package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.hero.Hero;
import pl.psi.player.PlayerMine;
import pl.psi.player.PlayerResources;

public class Mine implements MapElement {

    public enum ResourceType {
        GOLD, WOOD, ORE, MERCURY, SULFUR, CRYSTAL, GEMS
    }

    private final Mine.ResourceType type;

    public Mine(Mine.ResourceType aType) {
        this.type = aType;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero, HashBiMap map) {
        PlayerMine mines = aHero.getHeroStatistics().getPlayer().getMines();
        switch (type) {
            case GOLD:
                mines.getGoldMines().add(this);
                break;
            case WOOD:
                break;
            case ORE:
                break;
            case MERCURY:
                break;
            case SULFUR:
                break;
            case CRYSTAL:
                break;
            case GEMS:
                break;
        }
    }
}
