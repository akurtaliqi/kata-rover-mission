package fr.aku.rovermission;

import fr.aku.rovermission.application.Mission;
import fr.aku.rovermission.application.MissionPlan;
import fr.aku.rovermission.application.MissionRunner;
import fr.aku.rovermission.infrastructure.InputFileParser;
import fr.aku.rovermission.domain.Rover;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoverMissionApplication {

    private static final Logger LOGGER = Logger.getLogger(RoverMissionApplication.class.getName());

    private final InputFileParser inputFileParser = new InputFileParser();
    private final MissionRunner missionRunner = new MissionRunner();

    public static void main(String[] args) throws IOException {
        checkInputFilePath(args);

        String input = Files.readString(Path.of(args[0]));
        
        String output = new RoverMissionApplication().run(input);
        writeReport(output);
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

    public String run(String input) {
        MissionPlan missionPlan = inputFileParser.parseMissionPlan(input);

        return missionPlan.missions().stream()
            .map(mission -> runMission(missionPlan, mission))
            .collect(joining(System.lineSeparator()));
    }

    private String runMission(MissionPlan missionPlan, Mission mission) {
        missionRunner.run(mission, missionPlan.plateau());

        Rover rover = mission.rover();
        return "%d %d %s".formatted(
            rover.position().x(),
            rover.position().y(),
            rover.direction().code()
        );
    }

}
