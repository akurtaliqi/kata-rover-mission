package fr.aku.rovermission.application;

import fr.aku.rovermission.domain.Mission;
import fr.aku.rovermission.domain.Plateau;

public class MissionRunner {

    private final RoverCommandExecutor commandExecutor = new RoverCommandExecutor();

    public void run(Mission mission, Plateau plateau) {
        mission.commands().forEach(command -> 
            commandExecutor.execute(mission.rover(), command, plateau)
        );
    }
}
