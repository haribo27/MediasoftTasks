package ru.practicum.mediasoft.goalstatistics.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Team {

    private final String name;
    private Map<Player, List<Goal>> scorers = new HashMap<>();
    private int missedGoals;

    public Team(String name) {
        this.name = name;
    }

    public void addScorerWithGoal(Player scorer, Goal goal) {
        if (scorers.get(scorer) == null) {
            List<Goal> goals = new ArrayList<>();
            goals.add(goal);
            scorers.put(scorer, goals);
        } else {
            List<Goal> goals = scorers.get(scorer);
            goals.add(goal);
        }
    }

    public void setScorers(Map<Player, List<Goal>> scorers) {
        this.scorers = scorers;
    }

    public Map<Player, List<Goal>> getScorers() {
        return scorers;
    }

    public void incrementMissedGoals() {
        missedGoals = missedGoals + 1;
    }

    public int getMissedGoals() {
        return missedGoals;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
