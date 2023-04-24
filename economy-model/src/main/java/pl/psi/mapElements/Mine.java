package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.hero.Hero;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

public class Mine implements MapElement {

    public enum ResourceType {
        GOLD, WOOD, ORE, MERCURY, SULFUR, CRYSTAL, GEMS, RANDOM
    }

    private final Mine.ResourceType type;
    private Player currentOwner = null;

    public Mine(Mine.ResourceType aType) {
        this.type = aType;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero, HashBiMap map) {
        currentOwner = aHero.getHeroStatistics().getPlayer();
    }

    public void addResource() {
        // TODO add observer - not addResource
        if (currentOwner != null) {
            PlayerResources resources = currentOwner.getResources();
            switch (type) {
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
                case RANDOM:
                    // TODO Windmill
                    break;
            }
        }
    }
}
