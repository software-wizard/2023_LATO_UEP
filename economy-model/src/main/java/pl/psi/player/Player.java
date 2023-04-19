package pl.psi.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.hero.Hero;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {

    private final String name;
    private PlayerResources resources;

    public Player(String aName, PlayerResources aResources) {
        this.name = aName;
        this.resources = aResources;
    }
}
