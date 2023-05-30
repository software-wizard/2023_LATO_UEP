package pl.psi;

import java.util.List;

import WarMachines.WarMachineStatistic;
import org.junit.jupiter.api.Test;

import pl.psi.creatures.CastleCreatureFactory;
import pl.psi.warmachines.WarMachine;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class GameEngineTest
{
    @Test
    void shoudWorksHeHe()
    {
        final CastleCreatureFactory creatureFactory = new CastleCreatureFactory();
        final GameEngine gameEngine =
            new GameEngine(new Hero( List.of( creatureFactory.create( 1, false, 5 )), List.of(new WarMachine.Builder().statistic(WarMachineStatistic.CATAPULT).amount(1).build())),
                    new Hero(List.of( creatureFactory.create( 1, false, 5 )), List.of(new WarMachine.Builder().statistic(WarMachineStatistic.BALLISTA).amount(1).build())));

        gameEngine.attack( new Point( 1, 1 ) );
    }
}
