package fr.aku.rovermission;

import fr.aku.rovermission.application.Mission;
import fr.aku.rovermission.application.MissionPlan;
import fr.aku.rovermission.application.MissionRunner;
import fr.aku.rovermission.infrastructure.InputFileParser;
import fr.aku.rovermission.domain.Rover;

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
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected exactly one input file path");
        }

        String input = Files.readString(Path.of(args[0]));
        
        String output = new RoverMissionApplication().run(input);
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(System.lineSeparator());
        outputBuilder.append(output).append(System.lineSeparator());
        LOGGER.log(Level.INFO, outputBuilder.toString());
    }

    public String run(String input) {
        MissionPlan missionPlan = inputFileParser.parseMissionPlan(input);

        return missionPlan.missions().stream()
            .map(mission -> runMission(missionPlan, mission))
            .reduce((first, second) -> first + System.lineSeparator() + second)
            .orElse("");
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
