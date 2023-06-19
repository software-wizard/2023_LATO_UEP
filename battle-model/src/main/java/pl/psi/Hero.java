package pl.psi;

import java.util.HashMap;
import java.util.List;

import lombok.Setter;
import pl.psi.WarMachines.WarMachineStatistic;
import pl.psi.warmachines.WarMachine;
import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */

public class Hero
{
    @Getter
    private final List<Creature> creatures;
    @Getter
    private List<WarMachine> warMachines;
    @Setter
    @Getter
    private List<MapObjectIf> mapObjectIfs;
    private HashMap<String, Integer> skills = new HashMap<>();

    public Hero( final List< Creature > aCreatures, final List<WarMachine> aWarMachine)
    {
        creatures = aCreatures;
        warMachines = aWarMachine;
        loadSkills();
        indicateControl();
    }

    private void indicateControl() {
        ControlIndicator controlIndicator = new ControlIndicator(skills);
        for (WarMachine warMachine : warMachines) {
            if (controlIndicator.indicateControl((WarMachineStatistic) warMachine.getStats())){
                warMachine.setControllable();
            }

        }
    }

    public Hero( final List< Creature > aCreatures)
    {
        creatures = aCreatures;
    }

    public boolean isEnemy(MapObjectIf mapObjectIf1, MapObjectIf mapObjectIf2){
        return mapObjectIfs.contains(mapObjectIf1) != mapObjectIfs.contains(mapObjectIf2);
    }

    private void loadSkills(){
        for (WarMachine warMachine : warMachines) {
            skills.putAll(warMachine.getSkill());
        }
    }
}
