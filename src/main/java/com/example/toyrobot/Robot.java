package com.example.toyrobot;

import java.util.Objects;
import java.util.Optional;

public class Robot {

  private final Position position;
  private final Direction direction;
  private final Place place;

  public Robot(final Position position, final Direction direction, final Place place) {
    this.position = position;
    this.direction = direction;
    this.place = place;
  }

  public Optional<Robot> place() {
    return place.newPosition().apply(position)
        .map(newPosition -> new Robot(newPosition, direction, place));
  }

  public Robot move() {
    Optional<Position> maybeNewPosition = place.move(direction).apply(position);
    return maybeNewPosition
        .map(newPosition -> new Robot(newPosition, direction, place))
        .orElse(this);
  }

  public Robot left() {
    return new Robot(position, direction.left(), place);
  }

  public Robot right() {
    return new Robot(position, direction.right(), place);
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
