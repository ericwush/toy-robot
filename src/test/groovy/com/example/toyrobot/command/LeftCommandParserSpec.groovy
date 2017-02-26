package com.example.toyrobot.command

import spock.lang.Specification

class LeftCommandParserSpec extends Specification {

  def "test parse to left command"() {
    when:
    def command = new LeftCommandParser().parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == LeftCommand

    where:
    commandString << ["LEFT", " LEFT", "LEFT "]
  }

  def "test cannot parse to move command"() {
    when:
    def command = new LeftCommandParser().parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["left", "LEFT 1"]
  }

}
