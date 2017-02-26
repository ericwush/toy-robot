package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class MoveCommandSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test move on tabletop robot"() {
    when:
    def command = new MoveCommand()
    def robot = new Robot(position, Direction.NORTH, place)
    def context = new CommandContext(Optional.of(robot), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    newContext.maybeRobot.isPresent()
    newContext.maybeRobot.get() == new Robot(newPosition, Direction.NORTH, place)
    newContext.table == table
    !newContext.maybeOutput.isPresent()

    where:
    position << [new Position(1, 3), new Position(1, 4)]
    newPosition << [new Position(1, 4), new Position(1, 4)]
  }

  def "test move off tabletop robot"() {
    when:
    def command = new MoveCommand()
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }
}
