package com.example.toyrobot.command;

import java.util.Optional;

public class LeftCommandParser implements CommandParser {

  private static final String COMMAND_NAME = "LEFT";

  @Override
  public Optional<Command> parse(final String commandLineString) {
    if (isCommand(commandLineString, COMMAND_NAME)) {
      return Optional.of(new LeftCommand());
    }
    return Optional.empty();
  }

}
