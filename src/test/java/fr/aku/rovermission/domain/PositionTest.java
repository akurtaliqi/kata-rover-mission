package fr.aku.rovermission.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void move_north_from_one_two_gives_one_three() {
        Position start = new Position(1, 2);

        Position result = start.move(Direction.NORTH);

        assertEquals(new Position(1, 3), result);
    }

    @Test
    void move_west_from_one_two_gives_zero_two() {
        Position start = new Position(1, 2);

        Position result = start.move(Direction.WEST);

        assertEquals(new Position(0, 2), result);
    }
}
