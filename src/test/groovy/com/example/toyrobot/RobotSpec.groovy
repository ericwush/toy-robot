package com.example.toyrobot

import spock.lang.Specification

class RobotSpec extends Specification {

  def table = new Table(5)
  def movement = new Movement(table)

  def "test move"() {
    expect:
    new Robot(newPosition, direction, movement) == new Robot(position, direction, movement).move()

    where:
    direction << [Direction.NORTH, Direction.NORTH,
                  Direction.SOUTH, Direction.SOUTH,
                  Direction.WEST, Direction.WEST,
                  Direction.EAST, Direction.EAST]
    position << [new Position(0, 4), new Position(0, 3),
                 new Position(0, 1), new Position(0, 0),
                 new Position(1, 0), new Position(0, 0),
                 new Position(4, 0), new Position(3, 0)]
    newPosition << [new Position(0, 4), new Position(0, 4),
                    new Position(0, 0), new Position(0, 0),
                    new Position(0, 0), new Position(0, 0),
                    new Position(4, 0), new Position(4, 0)]
  }

  def "test left"() {
    def position = new Position(1, 2)

    expect:
    new Robot(position, newDirection, movement) == new Robot(position, direction, movement).left()

    where:
    direction << [Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST]
    newDirection << [Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH]
  }

  def "test right"() {
    def position = new Position(1, 2)

    expect:
    new Robot(position, newDirection, movement) == new Robot(position, direction, movement).right()

    where:
    direction << [Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST]
    newDirection << [Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH]
  }

  def "test report"() {
    expect:
    "1,2,NORTH" == new Robot(new Position(1, 2), Direction.NORTH, movement).report()
  }

}
