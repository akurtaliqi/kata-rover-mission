package fr.aku.rovermission.domain;

public class Rover {
    private Position position;
    private Direction direction;
    private Plateau plateau;

    public Rover() {
    }

    public Rover(Position position, Direction direction, Plateau plateau) {
        this.position = position;
        this.direction = direction;
        this.plateau = plateau;
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public void move() {
        Position newPosition = position.move(direction);
        if (isValidPosition(newPosition)) {
            position = newPosition;
        } else {
            throw new IllegalArgumentException("Invalid move: " + newPosition);
        }
    }

    public void execute(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
                case 'M' -> move();
                default -> throw new IllegalArgumentException("Unknown command: " + command);
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean isValidPosition(Position newPosition) {
        return plateau == null || plateau.isValidPosition(newPosition);
    }
}