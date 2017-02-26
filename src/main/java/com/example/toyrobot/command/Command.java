package com.example.toyrobot.command;

public interface Command {

  CommandContext execute(final CommandContext context);

}
