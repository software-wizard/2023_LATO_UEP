package pl.psi;

import pl.psi.creatures.Creature;

import java.util.List;

public interface IMovementOfCreatures {
    List<Point> move(int[][] aGrid, Point aStartingPoint, Point aEndingPoint);
}
