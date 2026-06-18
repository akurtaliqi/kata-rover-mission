package fr.aku.rovermission.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void north_turn_left_gives_west() {
        assertEquals(Direction.WEST, Direction.NORTH.left());
    }

    @Test
    void north_turn_right_gives_east() {
        assertEquals(Direction.EAST, Direction.NORTH.right());
    }

    @Test
    void east_turn_left_gives_north() {
        assertEquals(Direction.NORTH, Direction.EAST.left());
    }

    @Test
    void east_turn_right_gives_south() {
        assertEquals(Direction.SOUTH, Direction.EAST.right());
    }

    @Test
    void south_turn_left_gives_east() {
        assertEquals(Direction.EAST, Direction.SOUTH.left());
    }

    @Test
    void south_turn_right_gives_west() {
        assertEquals(Direction.WEST, Direction.SOUTH.right());
    }

    @Test
    void west_turn_left_gives_south() {
        assertEquals(Direction.SOUTH, Direction.WEST.left());
    }

    @Test
    void west_turn_right_gives_north() {
        assertEquals(Direction.NORTH, Direction.WEST.right());
    }
}
