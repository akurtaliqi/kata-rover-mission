package fr.aku.rovermission;

import fr.aku.rovermission.application.Mission;
import fr.aku.rovermission.application.MissionPlan;
import fr.aku.rovermission.application.MissionRunner;
import fr.aku.rovermission.domain.Command;
import fr.aku.rovermission.domain.Direction;
import fr.aku.rovermission.domain.Plateau;
import fr.aku.rovermission.domain.Position;
import fr.aku.rovermission.domain.Rover;
import fr.aku.rovermission.infrastructure.InputFileParser;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoverMissionApplication {

    private static final Logger LOGGER = Logger.getLogger(RoverMissionApplication.class.getName());

    private static final InputFileParser inputFileParser = new InputFileParser();
    private static final MissionRunner missionRunner = new MissionRunner();

    public static void main(String[] args) throws IOException {
        checkInputFilePath(args);
        List<String> parsedInput = inputFileParser.readLines(Path.of(args[0]));
        String output = run(parsedInput);
        writeReport(output);
    }

    public static String run(List<String> parsedInput) {
        MissionPlan missionPlan = parseMissionPlan(parsedInput);

        return missionPlan.missions().stream()
            .map(mission -> runMission(missionPlan, mission))
            .collect(joining(System.lineSeparator()));
    }

    private static void checkInputFilePath(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected exactly one input file path");
        }
    }

    private static void writeReport(String output) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(System.lineSeparator());
        outputBuilder.append(output).append(System.lineSeparator());
        LOGGER.log(Level.INFO, outputBuilder.toString());
    }

    private static MissionPlan parseMissionPlan(List<String> parsedInput) {
        Plateau plateau = parsePlateau(parsedInput.get(0));
        List<Mission> missions = new ArrayList<>();

        for (int i = 1; i < parsedInput.size(); i += 2) {
            Rover rover = parseRover(parsedInput.get(i));
            missions.add(new Mission(rover, parseCommands(parsedInput.get(i + 1))));
        }

        return new MissionPlan(plateau, missions);
    }

    private static List<Command> parseCommands(String commands) {
        return commands.chars()
            .mapToObj(command -> Command.fromCode((char) command))
            .toList();
    }

    private static Plateau parsePlateau(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid plateau line: " + line);
        }

        return new Plateau(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    private static Rover parseRover(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid rover line: " + line);
        }

        Position position = new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        Direction direction = Direction.fromCode(tokens[2]);
        return new Rover(position, direction);
    }

    private static String runMission(MissionPlan missionPlan, Mission mission) {
        missionRunner.run(mission, missionPlan.plateau());

        Rover rover = mission.rover();
        return "%d %d %s".formatted(
            rover.position().x(),
            rover.position().y(),
            rover.direction().code()
        );
    }

}
