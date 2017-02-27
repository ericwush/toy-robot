package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class RobotCommandSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test move on tabletop robot"() {
    when:
    def command = new RobotCommand(CommandType.MOVE)
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
    def command = new RobotCommand(CommandType.MOVE)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

  def "test left on tabletop robot"() {
    when:
    def command = new RobotCommand(CommandType.LEFT)
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
    def command = new RobotCommand(CommandType.LEFT)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

  def "test right on tabletop robot"() {
    when:
    def command = new RobotCommand(CommandType.RIGHT)
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
    newDirection << [Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH]
  }

  def "test right off tabletop robot"() {
    when:
    def command = new RobotCommand(CommandType.RIGHT)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

  def "test report on tabletop robot"() {
    when:
    def command = new RobotCommand(CommandType.REPORT)
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
    def command = new RobotCommand(CommandType.REPORT)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

}
