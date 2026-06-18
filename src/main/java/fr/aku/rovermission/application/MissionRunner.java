package fr.aku.rovermission.application;

import fr.aku.rovermission.domain.Command;
import fr.aku.rovermission.domain.Plateau;

public class MissionRunner {

    public void run(Mission mission, Plateau plateau) {
        for (Command command : mission.commands()) {
            switch (command) {
                case LEFT -> mission.rover().turnLeft();
                case RIGHT -> mission.rover().turnRight();
                case MOVE -> mission.rover().move(plateau);
            }
        }
    }
}
