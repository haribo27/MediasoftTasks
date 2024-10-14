package ru.practicum.mediasoft.goalstatistics.storage;

import ru.practicum.mediasoft.goalstatistics.model.Match;
import ru.practicum.mediasoft.goalstatistics.model.Team;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DataStorage {

    private Set<Team> teams = new HashSet<>();
    private Map<LocalDate, Match> matches = new HashMap<>();

    public void saveTeams(Team team) {
        teams.add(team);
    }

    public void saveMatch(Match match) {
        matches.put(match.getMatchDate(), match);
    }

    public Optional<Match> getMatch(LocalDate matchDate) {
        return Optional.ofNullable(matches.get(matchDate));
    }

    public Set<Team> getTeams() {
        return new HashSet<>(teams);
    }

    public Optional<Team> getTeamByName(String name) {
        return teams.stream().filter(team -> team.getName().equals(name)).findFirst();
    }
}
