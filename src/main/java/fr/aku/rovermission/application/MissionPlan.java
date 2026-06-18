package fr.aku.rovermission.application;

import java.util.List;

import fr.aku.rovermission.domain.Plateau;

public record MissionPlan(Plateau plateau, List<Mission> missions) {

}
