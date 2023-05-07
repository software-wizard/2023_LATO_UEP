package pl.psi.creatures;

import WarMachines.WarMachine;
import lombok.Getter;

import java.util.List;

public class NewHeroPrototype {
//    @Getter
//    private List< Creature > creatures;

    //    TODO should be final
    @Getter
    private List<WarMachine> warMachines;



//    public NewHeroPrototype( final List< Creature > aCreatures)
//    {
//        creatures = aCreatures;
//    }


//    public NewHeroPrototype( final List< Creature > aCreatures, final List<WarMachine> aWarMachine)
//    {
//        creatures = aCreatures;
//        warMachines = aWarMachine;
//    }

    public NewHeroPrototype( final List<WarMachine> aWarMachine)
    {
        warMachines = aWarMachine;
    }

}
