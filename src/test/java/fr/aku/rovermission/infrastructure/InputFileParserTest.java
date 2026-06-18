package fr.aku.rovermission.infrastructure;

import fr.aku.rovermission.domain.Command;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFileParserTest {

    private final InputFileParser parser = new InputFileParser();

    @Test
    void parse_lmlmlmlmm() {
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
        ), parser.parseCommands("LMLMLMLMM"));
    }

    @Test
    void reject_unknown_command() {
        assertThrows(IllegalArgumentException.class, () -> parser.parseCommands("LMX"));
    }
}
