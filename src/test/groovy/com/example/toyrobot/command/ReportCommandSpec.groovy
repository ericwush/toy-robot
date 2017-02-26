package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class ReportCommandSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test report on tabletop robot"() {
    when:
    def command = new ReportCommand()
    def position = new Position(1, 3)
    def direction = Direction.NORTH
    def robot = new Robot(position, direction, place)
    def context = new CommandContext(Optional.of(robot), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    newContext.maybeRobot.isPresent()
    newContext.maybeRobot.get() == new Robot(position, direction, place)
    newContext.table == table
    newContext.maybeOutput.isPresent()
    newContext.maybeOutput.get() == "1,3,NORTH"
  }

  def "test report off tabletop robot"() {
    when:
    def command = new ReportCommand()
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

}
