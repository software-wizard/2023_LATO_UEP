package pl.psi.player;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Player {

    private final String name;
    private PlayerResources resources;

    public Player(String aName, PlayerResources aResources) {
        this.name = aName;
        this.resources = aResources;
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

        if (this.getName() == player.getName()) {
            return true;
        } else {
            return false;
        }
    }

}
