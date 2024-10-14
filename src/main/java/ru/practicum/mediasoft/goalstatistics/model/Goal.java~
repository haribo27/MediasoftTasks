package ru.practicum.mediasoft.goalstatistics.model;

import java.util.Objects;

public class Goal {

    private Team team;
    private Player scorer;
    private int minute;
    private boolean ownGoal;
    private boolean penalty;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getScorer() {
        return scorer;
    }

    public void setScorer(Player scorer) {
        this.scorer = scorer;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(boolean ownGoal) {
        this.ownGoal = ownGoal;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return minute == goal.minute && ownGoal == goal.ownGoal && penalty == goal.penalty && Objects.equals(team, goal.team) && Objects.equals(scorer, goal.scorer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, scorer, minute, ownGoal, penalty);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "team=" + team +
                ", scorer=" + scorer +
                ", minute=" + minute +
                ", ownGoal=" + ownGoal +
                ", penalty=" + penalty +
                '}';
    }
}
