package com.example.toyrobot.command

import spock.lang.Specification

class ReportCommandParserSpec extends Specification {

  def "test parse to report command"() {
    when:
    def command = new ReportCommandParser().parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == ReportCommand

    where:
    commandString << ["REPORT", " REPORT", "REPORT "]
  }

  def "test cannot parse to report command"() {
    when:
    def command = new ReportCommandParser().parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["left", "LEFT 1"]
  }

}
