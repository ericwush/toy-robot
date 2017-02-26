package com.example.toyrobot;

import com.example.toyrobot.command.CommandContext;
import com.example.toyrobot.command.CommandExecutor;
import com.example.toyrobot.command.CommandLineParser;
import com.example.toyrobot.command.LeftCommandParser;
import com.example.toyrobot.command.MoveCommandParser;
import com.example.toyrobot.command.PlaceCommandParser;
import com.example.toyrobot.command.ReportCommandParser;
import com.example.toyrobot.command.RightCommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class App {

  // Main entrance of the application
  public static void main(final String[] args) throws IOException {
    List<CommandContext> contexts = new ArrayList<>();
    contexts.add(new CommandContext(Optional.empty(), new Table(5), Optional.empty()));

    PlaceCommandParser placeCommandParser = new PlaceCommandParser();
    MoveCommandParser moveCommandParser = new MoveCommandParser();
    LeftCommandParser leftCommandParser = new LeftCommandParser();
    RightCommandParser rightCommandParser = new RightCommandParser();
    ReportCommandParser reportCommandParser = new ReportCommandParser();

    CommandLineParser parser = new CommandLineParser(placeCommandParser, moveCommandParser,
        leftCommandParser, rightCommandParser, reportCommandParser);

    CommandExecutor executor = new CommandExecutor(parser);

    try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      Stream<String> commandLineString = in.lines();
      executor.execute(commandLineString, contexts);
    }
  }

}
