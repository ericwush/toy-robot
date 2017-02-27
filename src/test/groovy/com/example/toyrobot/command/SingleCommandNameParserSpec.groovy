package com.example.toyrobot.command

import spock.lang.Specification

class SingleCommandNameParserSpec extends Specification {

  def parser = new SingleCommandNameParser()

  def "test parse to move command"() {
    when:
    def command = parser.parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == MoveCommand

    where:
    commandString << ["MOVE", " MOVE", "MOVE "]
  }

  def "test cannot parse to move command"() {
    when:
    def command = parser.parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["move", "MOVE 1"]
  }

  def "test parse to left command"() {
    when:
    def command = parser.parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == LeftCommand

    where:
    commandString << ["LEFT", " LEFT", "LEFT "]
  }

  def "test cannot parse to left command"() {
    when:
    def command = parser.parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["left", "LEFT 1"]
  }

  def "test parse to right command"() {
    when:
    def command = parser.parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == RightCommand

    where:
    commandString << ["RIGHT", " RIGHT", "RIGHT "]
  }

  def "test cannot parse to right command"() {
    when:
    def command = parser.parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["right", "RIGHT 1"]
  }

  def "test parse to report command"() {
    when:
    def command = parser.parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == ReportCommand

    where:
    commandString << ["REPORT", " REPORT", "REPORT "]
  }

  def "test cannot parse to report command"() {
    when:
    def command = parser.parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["left", "LEFT 1"]
  }

}
