package pl.psi.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.mapElements.Mine;

import java.util.ArrayList;

@Getter
public class PlayerMine {

    private ArrayList<Mine> sawMills = new ArrayList<Mine>();
    private ArrayList<Mine> orePits = new ArrayList<Mine>();
    private ArrayList<Mine> crystalCaverns = new ArrayList<Mine>();
    private ArrayList<Mine> gemPonds = new ArrayList<Mine>();
    private ArrayList<Mine> alchemistsLabs = new ArrayList<Mine>();
    private ArrayList<Mine> sulfurDunes = new ArrayList<Mine>();
    private ArrayList<Mine> goldMines = new ArrayList<Mine>();

    public PlayerMine() {

    }

}
