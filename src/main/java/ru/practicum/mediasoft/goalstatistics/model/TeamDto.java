package ru.practicum.mediasoft.goalstatistics.model;

public class TeamDto {

    private String teamName;
    private int goals_scored;
    private int goals_missed;
    private PlayerDto top_scorer;

    public TeamDto(String teamName, PlayerDto top_scorer, int goals_missed, int goals_scored) {
        this.teamName = teamName;
        this.top_scorer = top_scorer;
        this.goals_missed = goals_missed;
        this.goals_scored = goals_scored;
    }

    public String getTeamName() {
        return teamName;
    }

    public PlayerDto getTop_scorer() {
        return top_scorer;
    }

    public int getGoals_missed() {
        return goals_missed;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "team=" + teamName +
                ", goals_scored=" + goals_scored +
                ", goals_missed=" + goals_missed +
                ", player=" + top_scorer +
                '}';
    }
}
