package fr.aku.rovermission.infrastructure.input;

import fr.aku.rovermission.domain.Command;

import java.util.List;

public class CommandParser {

    public List<Command> parse(String commands) {
        return commands.chars()
            .mapToObj(this::toCommand)
            .toList();
    }

    private Command toCommand(int command) {
        return switch (command) {
            case 'L' -> Command.LEFT;
            case 'R' -> Command.RIGHT;
            case 'M' -> Command.MOVE;
            default -> throw new IllegalArgumentException("Unknown command: " + (char) command);
        };
    }
}
