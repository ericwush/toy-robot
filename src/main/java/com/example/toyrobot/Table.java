package com.example.toyrobot;

import java.util.stream.IntStream;

public class Table {

  private final int dimensions;

  public Table(final int dimensions) {
    this.dimensions = dimensions;
  }

  public boolean isOnTabletop(final Position position) {
    return IntStream.range(0, dimensions).anyMatch(x -> x == position.getX()) &&
        IntStream.range(0, dimensions).anyMatch(y -> y == position.getY());
  }

}
