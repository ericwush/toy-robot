package com.example.toyrobot

import spock.lang.Specification

class PlaceSpec extends Specification {

  def table = new Table(5)

  def "test move east"() {
    when:
    def place = new Place(table)

    then:
    newPosition == place.move(Direction.EAST).apply(position)

    where:
    position << [new Position(3, 0), new Position(4, 0)]
    newPosition << [Optional.of(new Position(4, 0)), Optional.empty()]
  }

  def "test move west"() {
    when:
    def place = new Place(table)

    then:
    newPosition == place.move(Direction.WEST).apply(position)

    where:
    position << [new Position(1, 0), new Position(0, 0)]
    newPosition << [Optional.of(new Position(0, 0)), Optional.empty()]
  }

  def "test move north"() {
    when:
    def place = new Place(table)

    then:
    newPosition == place.move(Direction.NORTH).apply(position)

    where:
    position << [new Position(0, 3), new Position(0, 4)]
    newPosition << [Optional.of(new Position(0, 4)), Optional.empty()]
  }

  def "test move south"() {
    when:
    def place = new Place(table)

    then:
    newPosition == place.move(Direction.SOUTH).apply(position)

    where:
    position << [new Position(0, 1), new Position(0, 0)]
    newPosition << [Optional.of(new Position(0, 0)), Optional.empty()]
  }

  def "test new position"() {
    when:
    def place = new Place(table)

    then:
    newPosition == place.newPosition().apply(position)

    where:
    position << [new Position(0, 1), new Position(0, 6), new Position(-1, 3)]
    newPosition << [Optional.of(new Position(0, 1)), Optional.empty(), Optional.empty()]
  }

}
