package pl.psi.player;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Player {

    private final String name;
    private final PlayerResources resources;

    private String town;
    private String heroName;
    private String bonus;

    public Player(String aName, PlayerResources aResources, String town, String heroName, String bonus) {
        this.name = aName;
        this.resources = aResources;
        this.town = town;
        this.heroName = heroName;
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) { // TODO is ok?

        if (o == this) {
            return true;
        }

        if (!(o instanceof Player)) {
            return false;
        }

        Player player = (Player) o;

        return Objects.equals(this.getName(), player.getName());
    }

    public void setTown(String chosenTown) {
        this.town = chosenTown;
    }

    public void setHeroName(String chosenHero) {
        this.heroName = chosenHero;
    }

    public void setBonus(String chosenBonus) {
        this.bonus = chosenBonus;
    }
}
