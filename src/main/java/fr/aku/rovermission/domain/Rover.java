package fr.aku.rovermission.domain;

import java.util.logging.Logger;

public class Rover {

    private static final Logger LOGGER = Logger.getLogger(Rover.class.getName());
    private Position position;
    private Direction direction;

    public Rover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public void move(Plateau plateau) {
        Position newPosition = position.move(direction);
        if (plateau.isValidPosition(newPosition)) {
            position = newPosition;
        } else {
            LOGGER.warning("Move ignored: position %s is outside the plateau.".formatted(newPosition));
        }
    }

    public Position position() {
        return position;
    }

    public Direction direction() {
        return direction;
    }
}
