package com.example.toyrobot

import spock.lang.Specification

class RobotSpec extends Specification {

  def table = new Table(5)
  def movement = new Movement(table)

  def "test move"() {
    expect:
    new Robot(newPosition, direction, movement) == new Robot(position, direction, movement).move()

    where:
    direction << [Direction.North, Direction.North,
                  Direction.South, Direction.South,
                  Direction.West, Direction.West,
                  Direction.East, Direction.East]
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
    direction << [Direction.North, Direction.West, Direction.South, Direction.East]
    newDirection << [Direction.West, Direction.South, Direction.East, Direction.North]
  }

  def "test right"() {
    def position = new Position(1, 2)

    expect:
    new Robot(position, newDirection, movement) == new Robot(position, direction, movement).right()

    where:
    direction << [Direction.North, Direction.West, Direction.South, Direction.East]
    newDirection << [Direction.East, Direction.North, Direction.West, Direction.South]
  }

  def "test report"() {
    expect:
    "1,2,NORTH" == new Robot(new Position(1, 2), Direction.North, movement).report()
  }

}
