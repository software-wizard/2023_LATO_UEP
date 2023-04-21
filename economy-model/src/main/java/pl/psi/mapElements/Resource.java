package pl.psi.mapElements;

import pl.psi.hero.Hero;
import pl.psi.player.PlayerResources;

public class Resource implements MapElement {
    public enum ResourceType {
        GOLD, WOOD, ORE, MERCURY, SULFUR, CRYSTAL, GEMS
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
            case WOOD:
                resources.setWood(resources.getWood()+resourceNumber);
                break;
            case ORE:
                resources.setOre(resources.getOre()+resourceNumber);
                break;
            case MERCURY:
                resources.setMercury(resources.getMercury()+resourceNumber);
                break;
            case SULFUR:
                resources.setSulfur(resources.getSulfur()+resourceNumber);
                break;
            case CRYSTAL:
                resources.setCrystal(resources.getCrystal()+resourceNumber);
                break;
            case GEMS:
                resources.setGems(resources.getGems()+resourceNumber);
                break;
        }
    }
}
