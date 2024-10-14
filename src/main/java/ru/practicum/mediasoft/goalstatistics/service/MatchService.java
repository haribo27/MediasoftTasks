package ru.practicum.mediasoft.goalstatistics.service;

import ru.practicum.mediasoft.goalstatistics.exception.EntityNotFound;
import ru.practicum.mediasoft.goalstatistics.model.Goal;
import ru.practicum.mediasoft.goalstatistics.model.Match;
import ru.practicum.mediasoft.goalstatistics.model.Player;
import ru.practicum.mediasoft.goalstatistics.model.Team;
import ru.practicum.mediasoft.goalstatistics.storage.DataStorage;
import ru.practicum.mediasoft.goalstatistics.util.ClassManager;

import java.time.LocalDate;
import java.util.Optional;

public class MatchService {

    private final DataStorage storage = ClassManager.getDataStorage();
    private final TeamService teamService = ClassManager.getTeamService();


    public void createMatch(String[] parts) {
        LocalDate matchDate = LocalDate.parse(parts[0]);
        Team homeTeam = teamService.findTeamByName(parts[1]);
        Team awayTeam = teamService.findTeamByName(parts[2]);
        Optional<Match> maybeMatch = storage.getMatch(matchDate);
        if (maybeMatch.isPresent()) {
            Match match = maybeMatch.get();
            addGoalToMatch(match, parts, homeTeam, awayTeam);
            storage.saveMatch(match);
        } else {
            Match match = new Match();
            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setMatchDate(matchDate);
            addGoalToMatch(match, parts, homeTeam, awayTeam);
            storage.saveMatch(match);
        }
    }

    public void addGoalToMatch(Match match, String[] parts, Team homeTeam, Team awayTeam) {
        Goal goal = new Goal();
        Team scorerTeam = storage.getTeamByName(parts[3])
                .orElseThrow(() -> new EntityNotFound("Team with this name not found"));
        goal.setTeam(scorerTeam);
        Player scorer = new Player(parts[4]);
        goal.setScorer(scorer);
        String minute = parts[5];
        if (minute.equals("NA")) {
            goal.setMinute(0);
        } else {
            goal.setMinute(Integer.parseInt(parts[5]));
        }
        goal.setOwnGoal(Boolean.parseBoolean(parts[6].toLowerCase()));
        goal.setPenalty(Boolean.parseBoolean(parts[7].toLowerCase()));
        scorerTeam.addScorerWithGoal(scorer, goal);
        incrementConcededGoals(scorerTeam, homeTeam, awayTeam);
        match.addGoalToMatch(goal);
    }

    public void incrementConcededGoals(Team scorerTeam, Team homeTeam, Team awayTeam) {
        if (scorerTeam.equals(homeTeam)) {
            awayTeam.incrementMissedGoals();
        } else {
            homeTeam.incrementMissedGoals();
        }
    }

}
