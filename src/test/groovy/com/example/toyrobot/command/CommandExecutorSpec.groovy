package com.example.toyrobot.command

import com.example.toyrobot.Direction
import com.example.toyrobot.Place
import com.example.toyrobot.Position
import com.example.toyrobot.Robot
import com.example.toyrobot.Table
import spock.lang.Specification

class CommandExecutorSpec extends Specification {

  def table = new Table(5)
  def place = new Place(table)
  def placeCommandParser = new PlaceCommandParser()
  def moveCommandParser = new MoveCommandParser()
  def leftCommandParser = new LeftCommandParser()
  def rightCommandParser = new RightCommandParser()
  def reportCommandParser = new ReportCommandParser()
  def parser = new CommandLineParser(placeCommandParser, moveCommandParser,
      leftCommandParser, rightCommandParser, reportCommandParser)
  def executor = new CommandExecutor(parser)

  def "test execute valid commands"() {
    when:
    def commandStrings = ["PLACE 1,2,NORTH", "LEFT", "MOVE", "RIGHT", "REPORT"].stream()
    def contexts = [new CommandContext(Optional.empty(), table, Optional.empty())]
    executor.execute(commandStrings, contexts)
    def context = contexts.get(contexts.size() - 1)

    then:
    context.getMaybeRobot().isPresent()
    context.getMaybeRobot().get() == new Robot(new Position(0, 2), Direction.NORTH, place)
    context.getMaybeOutput().isPresent()
    context.getMaybeOutput().get() == "0,2,NORTH"
  }

  def "test place robot off tabletop"() {
    when:
    def commandStrings = ["PLACE 7,7,NORTH", "LEFT", "MOVE", "RIGHT", "REPORT"].stream()
    def contexts = [new CommandContext(Optional.empty(), table, Optional.empty())]
    executor.execute(commandStrings, contexts)
    def context = contexts.get(contexts.size() - 1)

    then:
    !context.getMaybeRobot().isPresent()
    !context.getMaybeOutput().isPresent()
  }

  def "test commands ignored until valid place"() {
    when:
    def commandStrings = ["LEFT", "MOVE", "RIGHT", "PLACE 1,2,NORTH", "REPORT"].stream()
    def contexts = [new CommandContext(Optional.empty(), table, Optional.empty())]
    executor.execute(commandStrings, contexts)
    def context = contexts.get(contexts.size() - 1)

    then:
    context.getMaybeRobot().isPresent()
    context.getMaybeRobot().get() == new Robot(new Position(1, 2), Direction.NORTH, place)
    context.getMaybeOutput().isPresent()
    context.getMaybeOutput().get() == "1,2,NORTH"
  }

  def "test invalid commands"() {
    when:
    def commandStrings = ["PLACE a,a,NORTH", "LEFT", "PLACE 1,1,NORTH", "RIGHT1", "report"].stream()
    def contexts = [new CommandContext(Optional.empty(), table, Optional.empty())]
    executor.execute(commandStrings, contexts)
    def context = contexts.get(contexts.size() - 1)

    then:
    context.getMaybeRobot().isPresent()
    context.getMaybeRobot().get() == new Robot(new Position(1, 1), Direction.NORTH, place)
    !context.getMaybeOutput().isPresent()
  }

}
