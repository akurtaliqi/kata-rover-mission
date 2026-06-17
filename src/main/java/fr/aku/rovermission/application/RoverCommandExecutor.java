package fr.aku.rovermission.application;

import fr.aku.rovermission.domain.Command;
import fr.aku.rovermission.domain.Plateau;
import fr.aku.rovermission.domain.Rover;

public class RoverCommandExecutor {

    public void execute(Rover rover, Command command, Plateau plateau) {
        switch (command) {
            case LEFT -> rover.turnLeft();
            case RIGHT -> rover.turnRight();
            case MOVE -> rover.move(plateau);
        }
    }
}
