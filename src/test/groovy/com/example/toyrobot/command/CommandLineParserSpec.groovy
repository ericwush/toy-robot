package com.example.toyrobot.command

import spock.lang.Specification

class CommandLineParserSpec extends Specification {

  def placeCommandParser = new PlaceCommandParser()
  def singleCommandNameParser = new SingleCommandNameParser()
  def parser = new CommandLineParser(placeCommandParser, singleCommandNameParser)

  def "test parsers parse command line to command"() {
    when:
    def maybeCommand = parser.parse(commandString)

    then:
    maybeCommand.isPresent()
    maybeCommand.get().class == commandClass

    where:
    commandString << ["PLACE 1,2,SOUTH", "MOVE", "LEFT", "RIGHT", "REPORT"]
    commandClass << [PlaceCommand, RobotCommand, RobotCommand, RobotCommand, RobotCommand]
  }

  def "test parsers cannot parse command line to command"() {
    when:
    def maybeCommand = parser.parse(commandString)

    then:
    !maybeCommand.isPresent()

    where:
    commandString << ["PLACE", "FOO", "REP"]
  }

}
