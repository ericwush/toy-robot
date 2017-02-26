package com.example.toyrobot;

import java.util.Optional;
import java.util.function.Function;

/**
 * I have knowledge about tabletop so I know positions on the tabletop
 */
public class Place {

  private final Table table;

  public Place(final Table table) {
    this.table = table;
  }

  public Function<Position, Optional<Position>> move(final Direction direction) {
    Function<Position, Optional<Position>> move = null;
    switch (direction) {
      case EAST:
        move = east().andThen(newPosition());
        break;
      case NORTH:
        move = north().andThen(newPosition());
        break;
      case SOUTH:
        move = south().andThen(newPosition());
        break;
      case WEST:
        move = west().andThen(newPosition());
        break;
    }
    return move;
  }

  public Function<Position, Optional<Position>> newPosition() {
    return newPosition -> table.isOnTabletop(newPosition) ? Optional.of(newPosition) : Optional.empty();
  }

  private Function<Position, Position> north() {
    return oldPosition -> new Position(oldPosition.getX(), oldPosition.getY() + 1);
  }

  private Function<Position, Position> south() {
    return oldPosition -> new Position(oldPosition.getX(), oldPosition.getY() - 1);
  }

  private Function<Position, Position> east() {
    return oldPosition -> new Position(oldPosition.getX() + 1, oldPosition.getY());
  }

  private Function<Position, Position> west() {
    return oldPosition -> new Position(oldPosition.getX() - 1, oldPosition.getY());
  }

}
