package fr.aku.rovermission.application;

import fr.aku.rovermission.domain.Plateau;

import java.util.List;

public record MissionPlan(Plateau plateau, List<Mission> missions) {
}
