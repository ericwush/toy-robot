package com.example.toyrobot

import spock.lang.Specification

class PositionSpec extends Specification {

  def "test position"() {
    when:
    def position = new Position(x, y)

    then:
    position.getX() == x
    position.getY() == y

    where:
    x << [1, 2]
    y << [4, 7]
  }

  def "test equals and hashcode"() {
    expect:
    new Position(1, 2) == new Position(1, 2)
    null != new Position(1, 2)
    new Object() != new Position(1, 2)
    new Position(1, 2).hashCode() == new Position(1, 2).hashCode()
  }

  def "test toString"() {
    expect:
    "1,2" == new Position(1, 2).toString()
  }

}
