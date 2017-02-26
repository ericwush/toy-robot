package com.example.toyrobot.command;

import java.util.Optional;

public class MoveCommandParser implements CommandParser {

  private static final String COMMAND_NAME = "MOVE";

  @Override
  public Optional<Command> parse(final String commandLineString) {
    if (isCommand(commandLineString, COMMAND_NAME)) {
      return Optional.of(new MoveCommand());
    }
    return Optional.empty();
  }

}
