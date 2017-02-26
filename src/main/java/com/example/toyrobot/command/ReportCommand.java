package com.example.toyrobot.command;

import com.example.toyrobot.Robot;

import java.util.Optional;

public class ReportCommand implements Command {

  @Override
  public CommandContext execute(final CommandContext context) {
    return context.getMaybeRobot()
        .map(Robot::report)
        .map(output -> new CommandContext(context.getMaybeRobot(), context.getTable(), Optional.of(output)))
        .orElse(context);
  }

}
