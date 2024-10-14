package ru.practicum.mediasoft.goalstatistics.service;

import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

public class TextHandlerService {

    private final TeamService teamService = ClassManager.getTeamService();
    private final MatchService matchService = ClassManager.getMatchService();

    public void handleText(String inputText) {
        String[] lines = inputText.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            teamService.createTeam(parts[1]);
            teamService.createTeam(parts[2]);

            matchService.createMatch(parts);
        }
    }
}
