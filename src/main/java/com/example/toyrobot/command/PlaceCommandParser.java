package com.example.toyrobot.command;

import com.example.toyrobot.Direction;
import com.example.toyrobot.Position;

import java.util.Optional;

public class PlaceCommandParser implements CommandParser {

  private static final CommandType COMMAND_TYPE = CommandType.PLACE;

  @Override
  public Optional<Command> parse(final String commandLineString) {
    String[] commandLineStrings = commandLineString.trim().split(" ");
    if (isCommand(commandLineStrings[0], COMMAND_TYPE)) {
      if (commandLineStrings.length != 2) {
        return Optional.empty();
      }

      String[] args = commandLineStrings[1].split(",");
      if (args.length != 3) {
        return Optional.empty();
      }

      return parsePosition(args)
          .map(maybePosition -> parseDirection(args)
              .map(maybeDirection -> new PlaceCommand(maybePosition, maybeDirection)))
          .filter(Optional::isPresent)
          .map(Optional::get);
    }
    return Optional.empty();
  }

  private Optional<Position> parsePosition(final String[] args) {
    try {
      return Optional.of(new Position(Integer.valueOf(args[0]), Integer.valueOf(args[1])));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private Optional<Direction> parseDirection(final String[] args) {
    return Direction.fromString(args[2]);
  }

}
