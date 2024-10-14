package ru.practicum.mediasoft.goalstatistics.model;

import java.util.Objects;

public class PlayerDto {

    private String name;
    private int goalsScored;

    public PlayerDto(String name, int goalsScored) {
        this.name = name;
        this.goalsScored = goalsScored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return goalsScored == playerDto.goalsScored && Objects.equals(name, playerDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, goalsScored);
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", goalsScored=" + goalsScored +
                '}';
    }
}
