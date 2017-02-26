package com.example.toyrobot.command

import spock.lang.Specification

class CommandLineParserSpec extends Specification {

  def placeCommandParser = new PlaceCommandParser()
  def moveCommandParser = new MoveCommandParser()
  def leftCommandParser = new LeftCommandParser()
  def rightCommandParser = new RightCommandParser()
  def reportCommandParser = new ReportCommandParser()
  def parsers = new CommandLineParser(placeCommandParser, moveCommandParser,
      leftCommandParser, rightCommandParser, reportCommandParser)

  def "test parsers parse command line to command"() {
    when:
    def maybeCommand = parsers.parse(commandString)

    then:
    maybeCommand.isPresent()
    maybeCommand.get().class == commandClass

    where:
    commandString << ["PLACE 1,2,SOUTH", "MOVE", "LEFT", "RIGHT", "REPORT"]
    commandClass << [PlaceCommand, MoveCommand, LeftCommand, RightCommand, ReportCommand]
  }

  def "test parsers cannot parse command line to command"() {
    when:
    def maybeCommand = parsers.parse(commandString)

    then:
    !maybeCommand.isPresent()

    where:
    commandString << ["PLACE", "FOO", "REP"]
  }

}
