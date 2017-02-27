package com.example.toyrobot.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * I can parse all commands that do not take any arguments
 */
public class SingleCommandNameParser implements CommandParser {

  private final static Map<CommandType, Class<? extends Command>> COMMAND_MAP = new HashMap<>();

  static {
    COMMAND_MAP.put(CommandType.MOVE, RobotCommand.class);
    COMMAND_MAP.put(CommandType.LEFT, RobotCommand.class);
    COMMAND_MAP.put(CommandType.RIGHT, RobotCommand.class);
    COMMAND_MAP.put(CommandType.REPORT, RobotCommand.class);
  }

  @Override
  public Optional<Command> parse(final String commandLineString) {
    Command command = null;

    Optional<Map.Entry<CommandType, Class<? extends Command>>> maybeCommand = COMMAND_MAP.entrySet().stream()
        .filter(entry -> isCommand(commandLineString, entry.getKey()))
        .findFirst();

    if (maybeCommand.isPresent()) {
      CommandType commandType = maybeCommand.get().getKey();
      Class<? extends Command> commandClass = maybeCommand.get().getValue();
      try {
        command = commandClass.getDeclaredConstructor(CommandType.class).newInstance(commandType);
      } catch (Exception e) {
        return Optional.empty();
      }
    }

    return Optional.ofNullable(command);
  }

}
