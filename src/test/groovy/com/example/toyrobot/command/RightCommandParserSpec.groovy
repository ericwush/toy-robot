package com.example.toyrobot.command

import spock.lang.Specification

class RightCommandParserSpec extends Specification {

  def "test parse to right command"() {
    when:
    def command = new RightCommandParser().parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == RightCommand

    where:
    commandString << ["RIGHT", " RIGHT", "RIGHT "]
  }

  def "test cannot parse to right command"() {
    when:
    def command = new RightCommandParser().parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["right", "RIGHT 1"]
  }

}
