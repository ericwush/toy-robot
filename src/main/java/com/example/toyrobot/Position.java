package com.example.toyrobot;

import java.util.Objects;

public class Position {

  private final int x;
  private final int y;

  public Position(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position other = (Position) o;

    return Objects.equals(x, other.x) && Objects.equals(y, other.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

}
