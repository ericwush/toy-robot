package com.example.toyrobot

import spock.lang.Specification

class DirectionSpec extends Specification {

  def "test direction from string"() {
    expect:
    direction == Direction.fromString(directionString)

    where:
    directionString << ["EAST", "WEST", "NORTH", "SOUTH", "INVALID", "", null]
    direction << [Optional.of(Direction.East), Optional.of(Direction.West), Optional.of(Direction.North),
                  Optional.of(Direction.South), Optional.empty(), Optional.empty(), Optional.empty()]
  }

  def "test left direction"() {
    expect:
    newDirection == direction.left()

    where:
    direction << [Direction.North, Direction.East, Direction.South, Direction.West]
    newDirection << [Direction.West, Direction.North, Direction.East, Direction.South]
  }

  def "test right direction"() {
    expect:
    newDirection == direction.right()

    where:
    direction << [Direction.North, Direction.East, Direction.South, Direction.West]
    newDirection << [Direction.East, Direction.South, Direction.West, Direction.North]
  }

}
