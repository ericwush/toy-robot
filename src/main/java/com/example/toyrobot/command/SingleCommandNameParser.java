package com.example.toyrobot.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * I can parse all commands that do not take any arguments
 */
public class SingleCommandNameParser implements CommandParser {

  private final static Map<String, Class<? extends Command>> COMMAND_MAP = new HashMap<>();

  static {
    COMMAND_MAP.put("MOVE", RobotCommand.class);
    COMMAND_MAP.put("LEFT", RobotCommand.class);
    COMMAND_MAP.put("RIGHT", RobotCommand.class);
    COMMAND_MAP.put("REPORT", RobotCommand.class);
  }

  @Override
  public Optional<Command> parse(final String commandLineString) {
    Command command = null;

//    Optional<? extends Class<? extends Command>> maybeCommandClass =
//        COMMAND_MAP.entrySet().stream()
//            .filter(entry -> isCommand(commandLineString, entry.getKey()))
//            .map(Map.Entry::getValue)
//            .findFirst();
//
//    if (maybeCommandClass.isPresent()) {
//      Class<? extends Command> commandClass = maybeCommandClass.get();
//      try {
//        command = commandClass.newInstance();
//      } catch (InstantiationException | IllegalAccessException e) {
//        return Optional.empty();
//      }
//    }

    Optional<Map.Entry<String, Class<? extends Command>>> maybeCommand = COMMAND_MAP.entrySet().stream()
        .filter(entry -> isCommand(commandLineString, entry.getKey()))
        .findFirst();

    if (maybeCommand.isPresent()) {
      String name = maybeCommand.get().getKey();
      Class<? extends Command> commandClass = maybeCommand.get().getValue();
      try {
        command = commandClass.getDeclaredConstructor(String.class).newInstance(name);
      } catch (Exception e) {
        return Optional.empty();
      }
    }

    return Optional.ofNullable(command);
  }

}
