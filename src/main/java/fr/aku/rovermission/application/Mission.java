package fr.aku.rovermission.application;

import java.util.List;

import fr.aku.rovermission.domain.Command;
import fr.aku.rovermission.domain.Rover;

public record Mission(Rover rover, List<Command> commands) {

}
