package com.example.toyrobot.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * I can parse all commands that do not take any arguments
 */
public class SingleCommandNameParser implements CommandParser {

  private final Map<String, Class<? extends Command>> singleCommandMap;

  public SingleCommandNameParser() {
    singleCommandMap = new HashMap<>();
    singleCommandMap.put("MOVE", MoveCommand.class);
    singleCommandMap.put("LEFT", LeftCommand.class);
    singleCommandMap.put("RIGHT", RightCommand.class);
    singleCommandMap.put("REPORT", ReportCommand.class);
  }

  @Override
  public Optional<Command> parse(final String commandLineString) {
    Command command = null;

    Optional<? extends Class<? extends Command>> maybeCommandClass =
        singleCommandMap.entrySet().stream()
            .filter(entry -> isCommand(commandLineString, entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst();

    if (maybeCommandClass.isPresent()) {
      Class<? extends Command> commandClass = maybeCommandClass.get();
      try {
        command = commandClass.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        return Optional.empty();
      }
    }

    return Optional.ofNullable(command);
  }

}
