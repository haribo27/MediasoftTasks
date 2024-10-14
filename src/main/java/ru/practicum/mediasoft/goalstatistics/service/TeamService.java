package ru.practicum.mediasoft.goalstatistics.service;

import ru.practicum.mediasoft.goalstatistics.exception.EntityNotFound;
import ru.practicum.mediasoft.goalstatistics.model.Goal;
import ru.practicum.mediasoft.goalstatistics.model.Player;
import ru.practicum.mediasoft.goalstatistics.model.PlayerDto;
import ru.practicum.mediasoft.goalstatistics.model.Team;
import ru.practicum.mediasoft.goalstatistics.storage.DataStorage;
import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamService {

    private final DataStorage storage = ClassManager.getDataStorage();

    public void createTeam(String teamName) {
        storage.saveTeams(new Team(teamName));
    }

    public Team findTeamByName(String name) {
        return storage.getTeamByName(name)
                .orElseThrow(() -> new EntityNotFound("Team with this name not found"));
    }

    public Set<Team> getAllTeams() {
        return storage.getTeams();
    }

    public PlayerDto getMvpPlayer(Team team) {
        return team.getScorers().entrySet()
                .stream()
                .max((entry1, entry2) -> {
                    int goals1 = entry1.getValue().size();
                    int goals2 = entry2.getValue().size();
                    if (goals1 != goals2) {
                        return Integer.compare(goals1, goals2);
                    } else {
                        int penalties1 = (int) entry1.getValue().stream().filter(Goal::isPenalty).count();
                        int penalties2 = (int) entry2.getValue().stream().filter(Goal::isPenalty).count();
                        return Integer.compare(penalties2, penalties1);
                    }
                })
                .map(Map.Entry::getKey)
                .map(player -> new PlayerDto(player.getName(), getPlayerGoals(team, player)))
                .orElse(null);
    }

    public int getTeamGoals(Team team) {
        return (int) team.getScorers().values()
                .stream()
                .flatMap(List::stream)
                .filter(goal -> !goal.isOwnGoal()).count();
    }

    public int getPlayerGoals(Team team, Player player) {
        return team.getScorers().get(player).size();
    }
}
