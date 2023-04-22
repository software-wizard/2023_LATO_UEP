package pl.psi.player;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Player {

    private final String name;
    private final PlayerResources resources;
    private final PlayerMine mines = new PlayerMine();

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

        return Objects.equals(this.getName(), player.getName());
    }

}
