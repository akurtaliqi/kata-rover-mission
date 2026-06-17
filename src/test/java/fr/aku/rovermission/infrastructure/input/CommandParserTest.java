package fr.aku.rovermission.infrastructure.input;

import fr.aku.rovermission.domain.Command;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    private final CommandParser parser = new CommandParser();

    @Test
    void parseLmlmlmlmm() {
        assertEquals(List.of(
            Command.LEFT,
            Command.MOVE,
            Command.LEFT,
            Command.MOVE,
            Command.LEFT,
            Command.MOVE,
            Command.LEFT,
            Command.MOVE,
            Command.MOVE
        ), parser.parse("LMLMLMLMM"));
    }

    @Test
    void rejectUnknownCommand() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("LMX"));
    }
}
