package com.example.toyrobot

import spock.lang.Specification

class MovementSpec extends Specification {

  def table = new Table(5)

  def "test move east"() {
    when:
    def movement = new Movement(table)

    then:
    newPosition == movement.get(Direction.East).apply(position)

    where:
    position << [new Position(3, 0), new Position(4, 0)]
    newPosition << [Optional.of(new Position(4, 0)), Optional.empty()]
  }

  def "test move west"() {
    when:
    def movement = new Movement(table)

    then:
    newPosition == movement.get(Direction.West).apply(position)

    where:
    position << [new Position(1, 0), new Position(0, 0)]
    newPosition << [Optional.of(new Position(0, 0)), Optional.empty()]
  }

  def "test move north"() {
    when:
    def movement = new Movement(table)

    then:
    newPosition == movement.get(Direction.North).apply(position)

    where:
    position << [new Position(0, 3), new Position(0, 4)]
    newPosition << [Optional.of(new Position(0, 4)), Optional.empty()]
  }

  def "test move south"() {
    when:
    def movement = new Movement(table)

    then:
    newPosition == movement.get(Direction.South).apply(position)

    where:
    position << [new Position(0, 1), new Position(0, 0)]
    newPosition << [Optional.of(new Position(0, 0)), Optional.empty()]
  }

}
