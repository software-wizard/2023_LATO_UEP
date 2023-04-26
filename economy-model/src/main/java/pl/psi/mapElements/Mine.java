package pl.psi.mapElements;

import com.google.common.collect.HashBiMap;
import pl.psi.Resource;
import pl.psi.hero.Hero;
import pl.psi.player.Player;
import pl.psi.player.PlayerResources;

public class Mine implements MapElement {

    private final Resource.Type type;
    private Player currentOwner = null;

    public Mine(Resource.Type aType) {
        this.type = aType;
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        currentOwner = aHero.getHeroStatistics().getPlayer();
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
            }
        }
    }
}
