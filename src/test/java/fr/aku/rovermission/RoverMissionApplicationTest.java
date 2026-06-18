package fr.aku.rovermission;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RoverMissionApplicationTest {

    @Test
    void sample_input_produces_expected_output() {
        var input = List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        );

        final String output = RoverMissionApplication.run(input);

        assertEquals(String.join(System.lineSeparator(),
                "1 3 N",
                "5 1 E"
        ), output);
    }
}
