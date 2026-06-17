package fr.aku.rovermission;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverMissionAppTest {

    @Test
    void sampleInputProducesExpectedOutput() {
        String input = String.join(System.lineSeparator(),
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
