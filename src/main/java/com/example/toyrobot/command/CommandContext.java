package com.example.toyrobot.command;

import com.example.toyrobot.Robot;
import com.example.toyrobot.Table;

import java.util.Optional;

public class CommandContext {

  private final Optional<Robot> maybeRobot;
  private final Table table;
  private final Optional<String> maybeOutput;

  public CommandContext(final Optional<Robot> maybeRobot, final Table table, final Optional<String> maybeOutput) {
    this.maybeRobot = maybeRobot;
    this.table = table;
    this.maybeOutput = maybeOutput;
  }

  public Optional<Robot> getMaybeRobot() {
    return maybeRobot;
  }

  public Table getTable() {
    return table;
  }

  public Optional<String> getMaybeOutput() {
    return maybeOutput;
  }

}
