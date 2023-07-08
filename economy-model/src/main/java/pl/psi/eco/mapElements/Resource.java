// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.mapElements;

import lombok.Getter;
import pl.psi.eco.ResourceType;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;
import pl.psi.eco.player.PlayerResources;

@Getter
public class Resource implements MapElement {

    private final ResourceType resourceType;
    private final int resourceAmount;

    public Resource(ResourceType aResourceType, int aResourceAmount) {
        this.resourceType = aResourceType;
        this.resourceAmount = aResourceAmount;
    }

    @Override
    public String getIcon() {
        switch (resourceType) {
            case GOLD:
                return "Resource_gold";
            case WOOD:
                return "Resource_wood";
            case ORE:
                return "Resource_ore";
            case MERCURY:
                return "Resource_mercury";
            case SULFUR:
                return "Resource_sulfur";
            case CRYSTAL:
                return "Resource_crystal";
            case GEMS:
                return "Resource_gem";
            default:
                return null;
        }
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
                resources.setGold(resources.getGold() + resourceAmount);
                break;
            case WOOD:
                resources.setWood(resources.getWood() + resourceAmount);
                break;
            case ORE:
                resources.setOre(resources.getOre() + resourceAmount);
                break;
            case MERCURY:
                resources.setMercury(resources.getMercury() + resourceAmount);
                break;
            case SULFUR:
                resources.setSulfur(resources.getSulfur() + resourceAmount);
                break;
            case CRYSTAL:
                resources.setCrystal(resources.getCrystal() + resourceAmount);
                break;
            case GEMS:
                resources.setGems(resources.getGems() + resourceAmount);
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
