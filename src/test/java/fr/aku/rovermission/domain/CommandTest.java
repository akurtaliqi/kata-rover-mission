package fr.aku.rovermission.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    void should_parse_known_command_code() {
        assertEquals(Command.LEFT, Command.fromCode('L'));
        assertEquals(Command.RIGHT, Command.fromCode('R'));
        assertEquals(Command.MOVE, Command.fromCode('M'));
    }

    @Test
    void should_reject_unknown_command_code() {
        assertThrows(IllegalArgumentException.class, () -> Command.fromCode('X'));
    }
}
