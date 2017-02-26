package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Position
import spock.lang.Specification

class PlaceCommandParserSpec extends Specification {

  def "test parse to place command"() {
    when:
    def command = new PlaceCommandParser().parse(commandString)

    then:
    command.isPresent()
    command.get().getClass() == PlaceCommand
    command.get().properties["position"] == position
    command.get().properties["direction"] == direction

    where:
    commandString << ["PLACE 1,2,NORTH", "PLACE 5,5,SOUTH", " PLACE 1,5,EAST", "PLACE 1,5,WEST "]
    position << [new Position(1, 2), new Position(5, 5), new Position(1, 5), new Position(1, 5)]
    direction << [Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST]
  }

  def "test cannot parse to place command"() {
    when:
    def command = new PlaceCommandParser().parse(commandString)

    then:
    !command.isPresent()

    where:
    commandString << ["PLACE  1,2,NORTH", "PLACE a,5,SOUTH", "place 1,2,NORTH", "PLACE 1,2,north",
                      "PLACE 1, 2,NORTH", "PLACE 1,2, NORTH", "PLACE 1,2,NORTH,SOUTH"]

  }

}
