package pl.psi.mapElements;

import pl.psi.hero.Hero;
import pl.psi.player.PlayerResources;

public class Resource implements MapElement {

    private final pl.psi.ResourceType resourceType;
    private final int resourceAmount;

    public Resource(pl.psi.ResourceType aResourceType, int aResourceAmount) {
        this.resourceType = aResourceType;
        this.resourceAmount = aResourceAmount;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        // Get resources from Player's instance
        PlayerResources resources = aHero.getHeroStatistics().getPlayer().getResources();
        switch (resourceType) {
            case GOLD:
                // Add gold for Player's resources
                resources.setGold(resources.getGold()+ resourceAmount);
                break;
            case WOOD:
                resources.setWood(resources.getWood()+ resourceAmount);
                break;
            case ORE:
                resources.setOre(resources.getOre()+ resourceAmount);
                break;
            case MERCURY:
                resources.setMercury(resources.getMercury()+ resourceAmount);
                break;
            case SULFUR:
                resources.setSulfur(resources.getSulfur()+ resourceAmount);
                break;
            case CRYSTAL:
                resources.setCrystal(resources.getCrystal()+ resourceAmount);
                break;
            case GEMS:
                resources.setGems(resources.getGems()+ resourceAmount);
                break;
        }
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return true;
    }

    @Override
    public void endOfTurn() {

    }
}
