package pl.psi.mapElements;

import lombok.Getter;
import pl.psi.hero.EconomyHero;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

@Getter
public class Resource implements MapElement {

    private final pl.psi.ResourceType resourceType;
    private final int resourceAmount;

    public Resource(pl.psi.ResourceType aResourceType, int aResourceAmount) {
        this.resourceType = aResourceType;
        this.resourceAmount = aResourceAmount;
    }

    @Override
    public String getIcon() {
        return "Resource";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {
        // Get resources from Player's instance
        PlayerResources resources = aPlayer.getResources();
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
