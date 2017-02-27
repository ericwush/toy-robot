package com.example.toyrobot;

import com.example.toyrobot.command.*;

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
    SingleCommandNameParser singleCommandNameParser = new SingleCommandNameParser();

    CommandLineParser parser = new CommandLineParser(placeCommandParser, singleCommandNameParser);

    CommandExecutor executor = new CommandExecutor(parser);

    try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      Stream<String> commandLineString = in.lines();
      executor.execute(commandLineString, contexts);
    }
  }

}
