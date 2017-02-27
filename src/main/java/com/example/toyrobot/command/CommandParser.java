package com.example.toyrobot.command;

import java.util.Optional;

public interface CommandParser {

  Optional<Command> parse(final String commandLineString);

  default boolean isCommand(final String commandLineString, final CommandType commandType) {
    return Optional.ofNullable(commandLineString)
        .filter(cmd -> cmd.trim().equals(commandType.name()))
        .isPresent();
  }

}
