package pl.psi;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int resources;
    @Setter
    private List<Hero> heroes = new ArrayList<>();
}
