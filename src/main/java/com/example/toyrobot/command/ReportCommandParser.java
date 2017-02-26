package com.example.toyrobot.command;

import java.util.Optional;

public class ReportCommandParser implements CommandParser {

  private static final String COMMAND_NAME = "REPORT";

  @Override
  public Optional<Command> parse(final String commandLineString) {
    if (isCommand(commandLineString, COMMAND_NAME)) {
      return Optional.of(new ReportCommand());
    }
    return Optional.empty();
  }

}
