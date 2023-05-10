package pl.psi;

import pl.psi.creatures.Creature;

import java.util.Optional;

public interface MapObjectIf {

    void attack(final Point point);

    boolean canMove(final Point point);

    boolean canAttack(final Point point);
    Optional<Creature> getCreature(final Point point);

    boolean isCurrentCreature(Point point);

}

