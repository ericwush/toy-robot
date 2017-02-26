package com.example.toyrobot;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {

  NORTH(1), EAST(2), SOUTH(3), WEST(4);

  private final int index;

  Direction(final int index) {
    this.index = index;
  }

  public static Optional<Direction> fromString(final String name) {
    return Arrays.stream(Direction.values()).filter(direction -> direction.name().equals(name)).findFirst();
  }

  public Direction left() {
    int i = index - 1 < 1 ? Direction.values().length : index - 1;
    return fromIndex(i).get();
  }

  public Direction right() {
    int i = index + 1 > Direction.values().length ? 1 : index + 1;
    return fromIndex(i).get();
  }

  private Optional<Direction> fromIndex(final int index) {
    return Arrays.stream(Direction.values()).filter(direction -> direction.index == index).findFirst();
  }

}
