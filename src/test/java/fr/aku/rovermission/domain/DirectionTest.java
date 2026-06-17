package fr.aku.rovermission.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void northTurnLeftGivesWest() {
        assertEquals(Direction.WEST, Direction.NORTH.left());
    }

    @Test
    void northTurnRightGivesEast() {
        assertEquals(Direction.EAST, Direction.NORTH.right());
    }
}
