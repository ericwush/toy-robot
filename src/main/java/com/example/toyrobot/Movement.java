package com.example.toyrobot;

import java.util.Optional;
import java.util.function.Function;

public class Movement {

  private final Table table;

  public Movement(final Table table) {
    this.table = table;
  }

  public Function<Position, Optional<Position>> get(final Direction direction) {
    Function<Position, Optional<Position>> move = null;
    switch (direction) {
      case East:
        move = east().andThen(newPosition());
        break;
      case North:
        move = north().andThen(newPosition());
        break;
      case South:
        move = south().andThen(newPosition());
        break;
      case West:
        move = west().andThen(newPosition());
        break;
    }
    return move;
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

  private Function<Position, Optional<Position>> newPosition() {
    return newPosition -> table.isOnTabletop(newPosition) ? Optional.of(newPosition) : Optional.empty();
  }

}
