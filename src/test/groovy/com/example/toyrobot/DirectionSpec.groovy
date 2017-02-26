package com.example.toyrobot

import spock.lang.Specification

class DirectionSpec extends Specification {

  def "test direction from string"() {
    expect:
    direction == Direction.fromString(directionString)

    where:
    directionString << ["EAST", "WEST", "NORTH", "SOUTH", "INVALID", "", null]
    direction << [Optional.of(Direction.EAST), Optional.of(Direction.WEST), Optional.of(Direction.NORTH),
                  Optional.of(Direction.SOUTH), Optional.empty(), Optional.empty(), Optional.empty()]
  }

  def "test left direction"() {
    expect:
    newDirection == direction.left()

    where:
    direction << [Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST]
    newDirection << [Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH]
  }

  def "test right direction"() {
    expect:
    newDirection == direction.right()

    where:
    direction << [Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST]
    newDirection << [Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH]
  }

}
