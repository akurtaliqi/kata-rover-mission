package fr.aku.rovermission.domain;

public class Rover {
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
            throw new IllegalArgumentException("Invalid move: " + newPosition);
        }
    }

    public Position position() {
        return position;
    }

    public Direction direction() {
        return direction;
    }
}