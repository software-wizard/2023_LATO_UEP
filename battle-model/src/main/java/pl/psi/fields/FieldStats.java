package pl.psi.fields;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldStats implements FieldStatisticIf{
    private final String name;
    private final int maxHp;
    private final String description;
}
