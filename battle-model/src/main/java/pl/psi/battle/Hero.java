// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.battle;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import pl.psi.battle.creatures.Creature;
import pl.psi.battle.warmachines.WarMachine;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */

public class Hero
{
    @Getter
    private final List<Creature> creatures;
    @Getter
    private List<WarMachine> warMachines = new ArrayList<>();

//    @Getter
//    private List<MapObjectIf> mapObjectIfs;
//    private final HashMap<String, Integer> skills = new HashMap<>();


    public Hero( final List< Creature > aCreatures, final List<WarMachine> aWarMachine)
    {
        checkDuplicates(aWarMachine);
        creatures = aCreatures;
        warMachines = aWarMachine;
//        loadSkills();
    }

    private void checkDuplicates(List<WarMachine> aWarMachine) {
        // Looking for duplicates in war machine list
        for (WarMachine warMachine : aWarMachine) {
            for (WarMachine warMachine1 : aWarMachine) {
                if ((warMachine != warMachine1) && (warMachine.getStats().equals(warMachine1.getStats()))) {
                    throw new IllegalStateException("War Machines cannot be duplicated");
                }
            }
        }
    }


    public Hero( final List< Creature > aCreatures)
    {
        creatures = aCreatures;
    }

//    public boolean isEnemy(MapObjectIf mapObjectIf1, MapObjectIf mapObjectIf2){
//        return mapObjectIfs.contains(mapObjectIf1) != mapObjectIfs.contains(mapObjectIf2);

//    private void loadSkills(){
//        for (WarMachine warMachine : warMachines) {
//            skills.putAll(warMachine.getSkill());
//        }
//    }
}
