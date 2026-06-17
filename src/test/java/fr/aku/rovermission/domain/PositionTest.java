package fr.aku.rovermission.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void moveNorthFromOneTwoGivesOneThree() {
        Position start = new Position(1, 2);

        Position result = start.move(Direction.NORTH);

        assertEquals(new Position(1, 3), result);
    }

    @Test
    void moveWestFromOneTwoGivesZeroTwo() {
        Position start = new Position(1, 2);

        Position result = start.move(Direction.WEST);

        assertEquals(new Position(0, 2), result);
    }
}
