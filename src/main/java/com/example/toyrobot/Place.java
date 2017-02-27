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
    Function<Position, Position> moveByDirection = null;

    switch (direction) {
      case EAST:
        moveByDirection = east();
        break;
      case NORTH:
        moveByDirection = north();
        break;
      case SOUTH:
        moveByDirection = south();
        break;
      case WEST:
        moveByDirection = west();
        break;
    }

    return moveByDirection.andThen(newPosition());
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
