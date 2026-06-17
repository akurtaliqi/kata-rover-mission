package fr.aku.rovermission.infrastructure.input;

import fr.aku.rovermission.application.MissionPlan;
import fr.aku.rovermission.domain.Direction;
import fr.aku.rovermission.domain.Mission;
import fr.aku.rovermission.domain.Plateau;
import fr.aku.rovermission.domain.Position;
import fr.aku.rovermission.domain.Rover;

import java.util.ArrayList;
import java.util.List;

public class MissionPlanParser {

    private final CommandParser commandParser = new CommandParser();

    public MissionPlan parse(String input) {
        List<String> lines = input.lines()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .toList();

        if (lines.size() < 3 || lines.size() % 2 == 0) {
            throw new IllegalArgumentException("Invalid mission input");
        }

        Plateau plateau = parsePlateau(lines.get(0));
        List<Mission> missions = new ArrayList<>();

        for (int index = 1; index < lines.size(); index += 2) {
            Rover rover = parseRover(lines.get(index));
            missions.add(new Mission(rover, commandParser.parse(lines.get(index + 1))));
        }

        return new MissionPlan(plateau, missions);
    }

    private Plateau parsePlateau(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid plateau line: " + line);
        }

        return new Plateau(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    private Rover parseRover(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid rover line: " + line);
        }

        Position position = new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        Direction direction = Direction.fromCode(tokens[2]);
        return new Rover(position, direction);
    }
}
