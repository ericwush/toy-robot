package com.example.toyrobot.command;

import com.example.toyrobot.Robot;

import java.util.Optional;

public class LeftCommand implements Command {

  @Override
  public CommandContext execute(final CommandContext context) {
    return context.getMaybeRobot()
        .map(Robot::left)
        .map(newRobot -> new CommandContext(Optional.of(newRobot), context.getTable(), Optional.empty()))
        .orElse(context);
  }

}