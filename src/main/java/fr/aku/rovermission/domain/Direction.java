package fr.aku.rovermission.domain;

public enum Direction {
    NORTH, 
    EAST, 
    SOUTH, 
    WEST;

    public Direction left() {
        return values()[(this.ordinal() + 3) % 4];
    }   

    public Direction right() {
        return values()[(this.ordinal() + 1) % 4];
    }
}