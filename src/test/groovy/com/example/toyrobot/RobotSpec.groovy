package com.example.toyrobot

import spock.lang.Specification

class RobotSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test place on tabletop"() {
    when:
    def position = new Position(1, 2)
    def maybeRobot = new Robot(position, Direction.NORTH, place).place()

    then:
    maybeRobot.isPresent()
    maybeRobot.get() == new Robot(position, Direction.NORTH, place)
  }

  def "test place off tabletop"() {
    when:
    def position = new Position(-1, 2)
    def maybeRobot = new Robot(position, Direction.NORTH, place).place()

    then:
    !maybeRobot.isPresent()
  }

  def "test move"() {
    expect:
    new Robot(newPosition, direction, place) == new Robot(position, direction, place).move()

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
    new Robot(position, newDirection, place) == new Robot(position, direction, place).left()

    where:
    direction << [Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST]
    newDirection << [Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH]
  }

  def "test right"() {
    def position = new Position(1, 2)

    expect:
    new Robot(position, newDirection, place) == new Robot(position, direction, place).right()

    where:
    direction << [Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST]
    newDirection << [Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH]
  }

  def "test report"() {
    expect:
    "1,2,NORTH" == new Robot(new Position(1, 2), Direction.NORTH, place).report()
  }

}
