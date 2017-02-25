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

}
