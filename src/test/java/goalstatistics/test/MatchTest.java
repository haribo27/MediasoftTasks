package goalstatistics.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.mediasoft.goalstatistics.model.Match;
import ru.practicum.mediasoft.goalstatistics.model.Team;
import ru.practicum.mediasoft.goalstatistics.service.MatchService;
import ru.practicum.mediasoft.goalstatistics.service.TeamService;
import ru.practicum.mediasoft.goalstatistics.storage.DataStorage;

import java.time.LocalDate;
import java.util.Optional;

public class MatchTest {

    private MatchService matchService;
    private TeamService teamService;
    private DataStorage storage;

    @Before
    public void setUp() {
        matchService = new MatchService();
        teamService = new TeamService();
        storage = new DataStorage();
    }

    @Test
    public void createMatch() {
        teamService.createTeam("1");
        teamService.createTeam("2");
        Match match = new Match();
        match.setMatchDate(LocalDate.parse("1916-07-02"));
        match.setHomeTeam(teamService.findTeamByName("1"));
        match.setAwayTeam(teamService.findTeamByName("2"));
        storage.saveMatch(match);
        Assert.assertEquals(storage.getMatch(LocalDate.parse("1916-07-02")).get().getAwayTeam().getName(),
                "2");
        Assert.assertEquals(storage.getMatch(LocalDate.parse("1916-07-02")).get().getHomeTeam().getName(),
                "1");
    }

    @Test
    public void getNotFoundMatchByMatchDate() {
        teamService.createTeam("1");
        teamService.createTeam("2");
        Match match = new Match();
        match.setMatchDate(LocalDate.parse("1916-07-02"));
        match.setHomeTeam(teamService.findTeamByName("1"));
        match.setAwayTeam(teamService.findTeamByName("2"));
        storage.saveMatch(match);
        Assert.assertEquals(storage.getMatch(LocalDate.parse("1916-07-01")), Optional.empty());
    }

    @Test
    public void addGoalToMatch() {
        String[] parts = {"1916-07-02", "Chile", "Uruguay", "Uruguay", "José Piendibene", "44", "FALSE", "FALSE"};
        teamService.createTeam(parts[1]);
        teamService.createTeam(parts[2]);
        Match match = new Match();
        match.setMatchDate(LocalDate.parse(parts[0]));
        Team homeTeam = teamService.findTeamByName(parts[1]);
        Team awayTeam = teamService.findTeamByName(parts[2]);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        matchService.addGoalToMatch(match, parts, homeTeam, awayTeam);
        storage.saveMatch(match);
        Assert.assertEquals(storage.getMatch(LocalDate.parse("1916-07-02"))
                .get()
                .getGoals()
                .getFirst()
                .getScorer().getName(), "José Piendibene");
        Assert.assertEquals(storage.getMatch(LocalDate.parse("1916-07-02"))
                .get()
                .getGoals().size(), 1);
    }

    @Test
    public void checkIncrementConcededGoals() {
        String[] parts = {"1916-07-02", "Chile", "Uruguay", "Uruguay", "José Piendibene", "44", "FALSE", "FALSE"};
        teamService.createTeam(parts[1]);
        teamService.createTeam(parts[2]);
        Match match = new Match();
        match.setMatchDate(LocalDate.parse(parts[0]));
        Team homeTeam = teamService.findTeamByName(parts[1]);
        Team awayTeam = teamService.findTeamByName(parts[2]);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        matchService.addGoalToMatch(match, parts, homeTeam, awayTeam);
        storage.saveMatch(match);
        Assert.assertEquals(homeTeam.getMissedGoals(), 1);
    }
}
