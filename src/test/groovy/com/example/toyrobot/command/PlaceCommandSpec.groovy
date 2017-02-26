package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class PlaceCommandSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)

  def "test place robot on tabletop"() {
    when:
    def position = new Position(1, 3)
    def command = new PlaceCommand(position, Direction.NORTH)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    newContext.maybeRobot.isPresent()
    newContext.maybeRobot.get() == new Robot(position, Direction.NORTH, place)
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

  def "test place robot off tabletop"() {
    when:
    def position = new Position(-1, 3)
    def command = new PlaceCommand(position, Direction.NORTH)
    def context = new CommandContext(Optional.empty(), table, Optional.empty())
    def newContext = command.execute(context)

    then:
    !newContext.maybeRobot.isPresent()
    newContext.table == table
    !newContext.maybeOutput.isPresent()
  }

}
