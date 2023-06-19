package pl.psi.mapElements;

import lombok.Getter;
import pl.psi.ResourceType;
import pl.psi.hero.EconomyHero;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

public class Mine implements MapElement {

    private final ResourceType resourceType;
    @Getter
    private Player currentOwner = null;

    public Mine(ResourceType aResourceType) {
        this.resourceType = aResourceType;
    }

    @Override
    public String getIcon() {
        switch (resourceType) {
            case GOLD:
                return "Gold_Mine";
            case WOOD:
                return "Sawmill";
            case ORE:
                return "Ore_Pit";
            case MERCURY:
                return "Alchemist's_Lab";
            case SULFUR:
                return "Sulfur_Dune";
            case CRYSTAL:
                return "Crystal_Cavern";
            case GEMS:
                return "Gem_Pond";
            default:
                return null;
        }
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero) {
        currentOwner = aEconomyHero.getHeroStatistics().getPlayer();
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {
        // add Resources if Player owns
        if (currentOwner != null) {
            PlayerResources resources = currentOwner.getResources();
            switch (resourceType) {
                case GOLD:
                    // Add gold for Player's resources
                    resources.setGold(resources.getGold()+1000);
                    break;
                case WOOD:
                    resources.setWood(resources.getWood()+2);
                    break;
                case ORE:
                    resources.setOre(resources.getOre()+2);
                    break;
                case MERCURY:
                    resources.setMercury(resources.getMercury()+1);
                    break;
                case SULFUR:
                    resources.setSulfur(resources.getSulfur()+1);
                    break;
                case CRYSTAL:
                    resources.setCrystal(resources.getCrystal()+1);
                    break;
                case GEMS:
                    resources.setGems(resources.getGems()+1);
                    break;
            }
        }
    }
}
