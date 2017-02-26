package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class LeftCommandSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test left on tabletop robot"() {
    when:
    def command = new LeftCommand()
    def position = new Position(1, 3)
    def robot = new Robot(position, direction, place)
    def context = new CommandContext(Optional.of(robot), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    newContext.maybeRobot.isPresent()
    newContext.maybeRobot.get() == new Robot(position, newDirection, place)
    newContext.table == table
    !newContext.maybeOutput.isPresent()

    where:
    direction << [Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST]
    newDirection << [Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH]
  }

  def "test left off tabletop robot"() {
    when:
    def command = new LeftCommand()
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

}
