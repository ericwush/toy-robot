package com.example.toyrobot

import spock.lang.Specification

class TableSpec extends Specification {

  def "test on tabletop"() {
    expect:
    new Table(5).isOnTabletop(new Position(x, y))

    where:
    x << (0 .. 4)
    y << (0 .. 4)
  }

  def "test off tabletop"() {
    expect:
    !(new Table(5).isOnTabletop(new Position(x, y)))

    where:
    x << [5, 0]
    y << [0, 5]
  }

}
