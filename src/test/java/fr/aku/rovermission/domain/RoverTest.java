package fr.aku.rovermission.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {

    @Test
    void sequenceLmlmlmlmmFromOneTwoNorthEndsAtOneThreeNorth() {
        Rover rover = new Rover(new Position(1, 2), Direction.NORTH, new Plateau(5, 5));

        rover.execute("LMLMLMLMM");

        assertEquals(new Position(1, 3), rover.getPosition());
        assertEquals(Direction.NORTH, rover.getDirection());
    }
}
