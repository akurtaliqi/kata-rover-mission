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
    void run_mission_from_one_two_north_ends_at_one_three_north() {
        Rover rover = new Rover(new Position(1, 2), Direction.NORTH);
        Mission mission = new Mission(rover, inputFileParser.parseCommands("LMLMLMLMM"));
        Plateau plateau = new Plateau(5, 5);

        missionRunner.run(mission, plateau);

        assertEquals(new Position(1, 3), rover.position());
        assertEquals(Direction.NORTH, rover.direction());
    }

    @Test
    void move_outside_plateau_is_ignored_and_rover_stays_in_place() {
        Rover rover = new Rover(new Position(5, 5), Direction.NORTH);
        Plateau plateau = new Plateau(5, 5);

        rover.move(plateau);

        assertEquals(new Position(5, 5), rover.position());
    }
}
