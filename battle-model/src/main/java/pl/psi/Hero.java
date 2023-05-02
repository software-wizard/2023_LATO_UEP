package pl.psi;

import java.util.List;

import WarMachines.WarMachine;
import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;

//    TODO should be final
    @Getter
    private List<WarMachine> warMachines;



    public Hero( final List< Creature > aCreatures)
    {
        creatures = aCreatures;
    }


    public Hero( final List< Creature > aCreatures, final List<WarMachine> aWarMachine)
    {
        creatures = aCreatures;
        warMachines = aWarMachine;
    }


}
