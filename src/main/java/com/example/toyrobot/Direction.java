package com.example.toyrobot;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {

  North(1, "NORTH"), East(2, "EAST"), South(3, "SOUTH"), West(4, "WEST");

  private final int index;
  private final String name;

  Direction(final int index, final String name) {
    this.index = index;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Optional<Direction> fromString(final String name) {
    return Arrays.stream(Direction.values()).filter(direction -> direction.name.equals(name)).findFirst();
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
