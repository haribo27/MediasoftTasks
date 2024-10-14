package goalstatistics.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.mediasoft.goalstatistics.exception.EntityNotFound;
import ru.practicum.mediasoft.goalstatistics.model.Goal;
import ru.practicum.mediasoft.goalstatistics.model.Player;
import ru.practicum.mediasoft.goalstatistics.model.Team;
import ru.practicum.mediasoft.goalstatistics.service.TeamService;
import ru.practicum.mediasoft.goalstatistics.storage.DataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamTest {

    private TeamService teamService;

    @Before
    public void setUp() {
        teamService = new TeamService();
    }

    @Test
    public void createTwoTeamsSaveAndCheck() {
        teamService.createTeam("Team1");
        teamService.createTeam("Team2");
        Assert.assertEquals(teamService.findTeamByName("Team1").getName(), "Team1");
        Assert.assertEquals(teamService.findTeamByName("Team2").getName(), "Team2");
    }

    @Test
    public void tryToCreateSameTeam() {
        teamService.createTeam("Team1");
        teamService.createTeam("Team1");
        Assert.assertEquals(teamService.findTeamByName("Team1").getName(), "Team1");
        Assert.assertEquals(teamService.getAllTeams().size(),1);
    }

    @Test
    public void tryToFindTeamIsNotExists() {
        Assert.assertThrows(EntityNotFound.class, () -> teamService.findTeamByName("Team1"));
    }

    @Test
    public void getMvpPlayer() {
        teamService.createTeam("Team1");
        Team team = teamService.findTeamByName("Team1");

        Player player = new Player("One");
        List<Goal> player1Goals = new ArrayList<>();

        Goal goal = new Goal();
        goal.setTeam(team);
        goal.setMinute(1);
        goal.setOwnGoal(false);
        goal.setPenalty(false);
        goal.setScorer(player);
        player1Goals.add(goal);

        Goal goal1 = new Goal();
        goal1.setTeam(team);
        goal1.setMinute(2);
        goal1.setOwnGoal(false);
        goal1.setPenalty(false);
        goal1.setScorer(player);
        player1Goals.add(goal1);

        Player player1 = new Player("Two");
        List<Goal> player2Goals = new ArrayList<>();

        Goal goal3 = new Goal();
        goal3.setTeam(team);
        goal3.setMinute(2);
        goal3.setOwnGoal(false);
        goal3.setPenalty(false);
        goal3.setScorer(player1);
        player2Goals.add(goal3);

        Map<Player, List<Goal>> scorers = new HashMap<>();
        scorers.put(player, player1Goals);
        scorers.put(player1, player2Goals);
        team.setScorers(scorers);
        Assert.assertEquals(teamService.getMvpPlayer(team).getName(),"One");
    }

    @Test
    public void getMvpIfTwoPlayersHaveEqualsGoalsButOneHasMoreByPenalties() {
        teamService.createTeam("Team1");
        Team team = teamService.findTeamByName("Team1");

        Player player = new Player("One");
        List<Goal> player1Goals = new ArrayList<>();

        Goal goal = new Goal();
        goal.setTeam(team);
        goal.setMinute(1);
        goal.setOwnGoal(false);
        goal.setPenalty(false);
        goal.setScorer(player);
        player1Goals.add(goal);

        Goal goal1 = new Goal();
        goal1.setTeam(team);
        goal1.setMinute(2);
        goal1.setOwnGoal(false);
        goal1.setPenalty(true);
        goal1.setScorer(player);
        player1Goals.add(goal1);

        Player player1 = new Player("Two");
        List<Goal> player2Goals = new ArrayList<>();

        Goal goal3 = new Goal();
        goal3.setTeam(team);
        goal3.setMinute(2);
        goal3.setOwnGoal(false);
        goal3.setPenalty(false);
        goal3.setScorer(player1);
        player2Goals.add(goal3);

        Goal goal4 = new Goal();
        goal.setTeam(team);
        goal4.setMinute(2);
        goal4.setOwnGoal(false);
        goal4.setPenalty(false);
        goal4.setScorer(player1);
        player2Goals.add(goal4);

        Map<Player, List<Goal>> scorers = new HashMap<>();
        scorers.put(player, player1Goals);
        scorers.put(player1, player2Goals);
        team.setScorers(scorers);
        Assert.assertEquals(teamService.getMvpPlayer(team).getName(),"Two");
    }

    @Test
    public void getTwoTeamGoals() {
        teamService.createTeam("Team1");
        Team team = teamService.findTeamByName("Team1");

        Player player = new Player("One");
        List<Goal> player1Goals = new ArrayList<>();

        Goal goal = new Goal();
        goal.setTeam(team);
        goal.setMinute(1);
        goal.setOwnGoal(false);
        goal.setPenalty(false);
        goal.setScorer(player);
        player1Goals.add(goal);

        Goal goal1 = new Goal();
        goal1.setTeam(team);
        goal1.setMinute(2);
        goal1.setOwnGoal(false);
        goal1.setPenalty(true);
        goal1.setScorer(player);
        player1Goals.add(goal1);

        Player player1 = new Player("Two");
        List<Goal> player2Goals = new ArrayList<>();

        Goal goal3 = new Goal();
        goal3.setTeam(team);
        goal3.setMinute(2);
        goal3.setOwnGoal(false);
        goal3.setPenalty(false);
        goal3.setScorer(player1);
        player2Goals.add(goal3);

        Goal goal4 = new Goal();
        goal.setTeam(team);
        goal4.setMinute(2);
        goal4.setOwnGoal(false);
        goal4.setPenalty(false);
        goal4.setScorer(player1);
        player2Goals.add(goal4);

        Map<Player, List<Goal>> scorers = new HashMap<>();
        scorers.put(player, player1Goals);
        scorers.put(player1, player2Goals);
        team.setScorers(scorers);
        Assert.assertEquals(teamService.getTeamGoals(team),4);
    }

}
