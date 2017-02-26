package com.example.toyrobot.command;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * I parse commands and execute valid ones
 */
public class CommandExecutor {

  private final CommandParser parser;

  public CommandExecutor(final CommandParser parser) {
    this.parser = parser;
  }

  public void execute(final Stream<String> commandLineString, final List<CommandContext> contexts) {
    commandLineString
        .map(parser::parse)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(executeCommand(contexts))
        .peek(output())
        .forEach(contexts::add);
  }

  private Function<Command, CommandContext> executeCommand(final List<CommandContext> contexts) {
    return command -> command.execute(contexts.get(contexts.size() - 1));
  }

  private Consumer<CommandContext> output() {
    return context -> {
        if (context.getMaybeOutput().isPresent()) {
          System.out.println(context.getMaybeOutput().get());
        }
      };
  }

}
