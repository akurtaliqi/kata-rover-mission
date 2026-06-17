package fr.aku.rovermission.domain;

public enum Direction {
    NORTH, 
    EAST, 
    SOUTH, 
    WEST;

    public static Direction fromCode(String code) {
        return switch (code) {
            case "N" -> NORTH;
            case "E" -> EAST;
            case "S" -> SOUTH;
            case "W" -> WEST;
            default -> throw new IllegalArgumentException("Unknown direction: " + code);
        };
    }

    public String code() {
        return switch (this) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public Direction left() {
        return values()[(this.ordinal() + 3) % 4];
    }   

    public Direction right() {
        return values()[(this.ordinal() + 1) % 4];
    }
}