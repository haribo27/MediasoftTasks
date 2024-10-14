package ru.practicum.mediasoft.goalstatistics.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.practicum.mediasoft.goalstatistics.model.TeamDto;
import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

import java.util.List;

public class OutputResultService {

    private final TeamService teamService = ClassManager.getTeamService();

    public List<TeamDto> getTeamDto() {
        return teamService.getAllTeams().stream()
                .map(team -> new TeamDto(team.getName(), teamService.getMvpPlayer(team),
                        team.getMissedGoals(), teamService.getTeamGoals(team)))
                .sorted((team1, team2) -> team1.compare(team1, team2))
                .toList();
    }

    public String getTeamsAsJson() {
        try {
            ObjectMapper prettyMapper = new ObjectMapper();
            prettyMapper.enable(SerializationFeature.INDENT_OUTPUT);

            return prettyMapper.writeValueAsString(getTeamDto());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return "{}";
        }
    }
}
