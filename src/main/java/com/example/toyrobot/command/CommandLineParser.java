package com.example.toyrobot.command;

import java.util.Arrays;
import java.util.Optional;

public class CommandLineParser implements CommandParser {

  private final CommandParser[] parsers;

  public CommandLineParser(final CommandParser... parsers) {
    this.parsers = parsers;
  }

  @Override
  public Optional<Command> parse(final String commandLineString) {
    return Arrays.stream(parsers)
        .map(parser -> parser.parse(commandLineString))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();
  }

}
