package WarMachines;

import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WarMachineStats implements WarMachineStatisticIf{
    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int shotRange;
    private final Range< Integer > damage;
    private final int tier;
    private final String description;
    private final boolean isUpgraded;
    private final int hexSize;
}
