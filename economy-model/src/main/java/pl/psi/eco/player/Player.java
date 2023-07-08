// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.eco.hero.EconomyHero;

import java.awt.*;

@Getter
@Setter
@Builder
public class Player {

    private final String name;
    private final PlayerResources resources;

    private String town;
    private String heroName;
    private String bonus;

    private EconomyHero economyHero;
    private Color color;

    public Player(String aName, PlayerResources aResources, String town, String heroName, String bonus,
                  EconomyHero aEconomyHero, Color aColor) {
        this.name = aName;
        this.resources = aResources;
        this.town = town;
        this.heroName = heroName;
        this.bonus = bonus;
        this.economyHero = aEconomyHero;
        this.color = aColor;
    }
}
