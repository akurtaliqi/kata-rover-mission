package fr.aku.rovermission.infrastructure;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class InputFileParserTest {

    private final InputFileParser parser = new InputFileParser();

    @Test
    void read_lines_from_input_file() throws Exception {
        Path inputFile = Files.createTempFile("rover-input", ".txt");
        Files.writeString(inputFile, String.join(System.lineSeparator(),
                "5 5",
                "1 2 N",
                "LMLMLMLMM"
        ));

        assertEquals(List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM"
        ), parser.readLines(inputFile));
    }
}
