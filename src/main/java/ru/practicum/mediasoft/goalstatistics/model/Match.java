package ru.practicum.mediasoft.goalstatistics.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Match {

    private LocalDate matchDate;
    private Team homeTeam;
    private Team awayTeam;
    private List<Goal> goals;

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void addGoalToMatch(Goal goal) {
        if (goals == null) {
            goals = new ArrayList<>();
        }
        if (goal != null) {
            goals.add(goal);
        }
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchDate, match.matchDate) && Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam) && Objects.equals(goals, match.goals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchDate, homeTeam, awayTeam, goals);
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchDate=" + matchDate +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", goals=" + goals +
                '}';
    }
}
