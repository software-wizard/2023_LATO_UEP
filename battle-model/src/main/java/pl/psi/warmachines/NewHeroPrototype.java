package pl.psi.warmachines;

import WarMachines.WarMachine;
import lombok.Getter;
import pl.psi.creatures.Creature;

import java.util.List;

public class NewHeroPrototype {
    @Getter
    private final List<Creature> creatures;
    @Getter
    private final List<WarMachine> warMachines;

    public NewHeroPrototype( final List< Creature > aCreatures, final List<WarMachine> aWarMachine)
    {
        creatures = aCreatures;
        warMachines = aWarMachine;
    }
}
