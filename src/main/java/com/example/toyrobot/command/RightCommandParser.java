package com.example.toyrobot.command;

import java.util.Optional;

public class RightCommandParser implements CommandParser {

  private static final String COMMAND_NAME = "RIGHT";

  @Override
  public Optional<Command> parse(final String commandLineString) {
    if (isCommand(commandLineString, COMMAND_NAME)) {
      return Optional.of(new RightCommand());
    }
    return Optional.empty();
  }

}
