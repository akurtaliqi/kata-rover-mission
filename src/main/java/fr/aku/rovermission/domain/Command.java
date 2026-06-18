package fr.aku.rovermission.domain;

public enum Command {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    private final char code;

    Command(char code) {
        this.code = code;
    }

    public static Command fromCode(char code) {
        return switch (code) {
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            case 'M' -> MOVE;
            default -> throw new IllegalArgumentException("Unknown command: " + code);
        };
    }

    public char code() {
        return code;
    }
}
