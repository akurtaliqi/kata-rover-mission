package fr.aku.rovermission.domain;

import java.util.List;

public record Mission(Rover rover, List<Command> commands) {
}
