package com.example.toyrobot;

import java.util.Objects;
import java.util.Optional;

public class Robot {

  private final Position position;
  private final Direction direction;
  private final Movement movement;

  public Robot(final Position position, final Direction direction, final Movement movement) {
    this.position = position;
    this.direction = direction;
    this.movement = movement;
  }

  public Robot move() {
    Optional<Position> maybeNewPosition = movement.get(direction).apply(position);
    return maybeNewPosition
        .map(newPosition -> new Robot(newPosition, direction, movement))
        .orElse(this);
  }

  public Robot left() {
    return new Robot(position, direction.left(), movement);
  }

  public Robot right() {
    return new Robot(position, direction.right(), movement);
  }

  public String report() {
    return position.toString() + "," + direction.name();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Robot robot = (Robot) o;
    return Objects.equals(position, robot.position) && direction == robot.direction;
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, direction);
  }

}
