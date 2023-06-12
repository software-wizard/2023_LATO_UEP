package pl.psi.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerResources {

    private int wood;
    private int ore;
    private int gold;
    private int mercury;
    private int sulfur;
    private int crystal;
    private int gems;

}
// adnotacja @value