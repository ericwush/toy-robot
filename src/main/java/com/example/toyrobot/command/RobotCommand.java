package com.example.toyrobot.command;

import com.example.toyrobot.Robot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * I execute stateless robot related commands
 */
public class RobotCommand implements Command {

  private final static Map<String, Function<CommandContext, CommandContext>> COMMAND_MAP = new HashMap<>();

  static {
    COMMAND_MAP.put("MOVE", move());
    COMMAND_MAP.put("LEFT", left());
    COMMAND_MAP.put("RIGHT", right());
    COMMAND_MAP.put("REPORT", report());
  }

  private final String name;

  public RobotCommand(final String name) {
    this.name = name;
  }

  @Override
  public CommandContext execute(final CommandContext context) {
    return COMMAND_MAP.get(name).apply(context);
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
