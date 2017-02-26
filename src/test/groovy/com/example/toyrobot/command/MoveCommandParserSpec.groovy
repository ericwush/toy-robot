package com.example.toyrobot.command

import spock.lang.Specification

class MoveCommandParserSpec extends Specification {

  def "test parse to move command"() {
    when:
    def command = new MoveCommandParser().parse("MOVE")

    then:
    command.isPresent()
    command.get().getClass() == MoveCommand
  }

  def "test cannot parse to move command"() {
    when:
    def command = new MoveCommandParser().parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["move", "MOVE 1"]
  }

}
