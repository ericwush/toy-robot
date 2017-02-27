package com.example.toyrobot.command;

import com.example.toyrobot.Robot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * I execute stateless robot related commands
 */
public class StatelessCommand implements Command {

  private final static Map<CommandType, Function<CommandContext, CommandContext>> COMMAND_MAP = new HashMap<>();

  static {
    COMMAND_MAP.put(CommandType.MOVE, move());
    COMMAND_MAP.put(CommandType.LEFT, left());
    COMMAND_MAP.put(CommandType.RIGHT, right());
    COMMAND_MAP.put(CommandType.REPORT, report());
  }

  private final CommandType commandType;

  public StatelessCommand(final CommandType commandType) {
    this.commandType = commandType;
  }

  @Override
  public CommandContext execute(final CommandContext context) {
    return COMMAND_MAP.get(commandType).apply(context);
  }

  private static Function<CommandContext, CommandContext> right() {
    return context -> context.getMaybeRobot()
        .map(Robot::right)
        .map(newCommandContext(context))
        .orElse(context);
  }

  private static Function<CommandContext, CommandContext> left() {
    return context -> context.getMaybeRobot()
        .map(Robot::left)
        .map(newCommandContext(context))
        .orElse(context);
  }

  private static Function<CommandContext, CommandContext> move() {
    return context -> context.getMaybeRobot()
        .map(Robot::move)
        .map(newCommandContext(context))
        .orElse(context);
  }

  private static Function<CommandContext, CommandContext> report() {
    return context -> context.getMaybeRobot()
        .map(Robot::report)
        .map(output -> new CommandContext(context.getMaybeRobot(), context.getTable(), Optional.of(output)))
        .orElse(context);
  }

  private static Function<Robot, CommandContext> newCommandContext(final CommandContext context) {
    return newRobot -> new CommandContext(Optional.of(newRobot), context.getTable(), Optional.empty());
  }

}
