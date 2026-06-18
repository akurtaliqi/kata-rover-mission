package fr.aku.rovermission.domain;

import fr.aku.rovermission.application.Mission;
import fr.aku.rovermission.application.MissionRunner;
import fr.aku.rovermission.infrastructure.InputFileParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    private final InputFileParser inputFileParser = new InputFileParser();
    private final MissionRunner missionRunner = new MissionRunner();

    @Test
    void runMissionFromOneTwoNorthEndsAtOneThreeNorth() {
        Rover rover = new Rover(new Position(1, 2), Direction.NORTH);
        Mission mission = new Mission(rover, inputFileParser.parseCommands("LMLMLMLMM"));
        Plateau plateau = new Plateau(5, 5);

        missionRunner.run(mission, plateau);

        assertEquals(new Position(1, 3), rover.position());
        assertEquals(Direction.NORTH, rover.direction());
    }
}
