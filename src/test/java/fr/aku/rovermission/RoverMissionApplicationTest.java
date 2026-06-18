package fr.aku.rovermission;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

class RoverMissionApplicationTest {

    @Test
    void sample_input_produces_expected_output() {
        List<String> input = List.of(
            "5 5",
            "1 2 N",
            "LMLMLMLMM",
            "3 3 E",
            "MMRMMRMRRM"
        );

        String output = new RoverMissionApplication().run(input);

        assertEquals(String.join(System.lineSeparator(),
            "1 3 N",
            "5 1 E"
        ), output);
    }
}
