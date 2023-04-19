package pl.psi.mapElements;

import pl.psi.hero.Hero;
import pl.psi.player.PlayerResources;

public class Resource implements MapElement {
    public enum ResourceType {
        GOLD, WOOD, STONE
    }

    private final ResourceType type;
    private final int resourceNumber;

    public Resource(ResourceType aType, int aResourceNumber) {
        this.type = aType;
        this.resourceNumber = aResourceNumber;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        // Get resources from Player's instance
        PlayerResources resources = aHero.getHeroStatistics().getPlayer().getResources();
        switch (type) {
            case GOLD:
                // Add gold for Player's resources
                resources.setGold(resources.getGold()+resourceNumber);
                break;
        }
    }
}
