package fr.aku.rovermission.domain;

import java.util.Arrays;

public enum Direction {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N");

    private final String leftCode;
    private final String rightCode;
    private final String code;

    Direction(String code, String leftCode, String rightCode) {
        this.code = code;
        this.leftCode = leftCode;
        this.rightCode = rightCode;
    }

    public static Direction fromCode(String code) {
        return Arrays.stream(values())
                .filter(direction -> direction.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown direction: " + code));
    }

    public String code() {
        return code;
    }

    public Direction left() {
        return fromCode(leftCode);
    }

    public Direction right() {
        return fromCode(rightCode);
    }
}
