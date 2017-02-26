package com.example.toyrobot.command;

import com.example.toyrobot.Direction;
import com.example.toyrobot.Place;
import com.example.toyrobot.Position;
import com.example.toyrobot.Robot;

import java.util.Optional;

public class PlaceCommand implements Command {

  private final Position position;
  private final Direction direction;

  public PlaceCommand(final Position position, final Direction direction) {
    this.position = position;
    this.direction = direction;
  }

  @Override
  public CommandContext execute(final CommandContext context) {
    Robot anyRobot = new Robot(position, direction, new Place(context.getTable()));
    return new CommandContext(
        anyRobot.place(),
        context.getTable(),
        Optional.empty());
  }

  public Position getPosition() {
    return position;
  }

  public Direction getDirection() {
    return direction;
  }

}
