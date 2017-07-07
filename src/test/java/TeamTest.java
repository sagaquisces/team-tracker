import org.junit.*;
import static org.junit.Assert.*;

public class TeamTest {

  @Test
  public void team_instantiatesCorrectly_true() {
    Team testTeam = new Team("teamBlue");
    assertEquals(true, testTeam instanceof Team);
  }

  @Test
  public void getName_teamInstantiatesWithName_teamBlue() {
    Team testTeam = new Team("teamBlue");
    assertEquals("teamBlue", testTeam.getName());
  }

  @Test
  public void all_returnsAllInstancesOfTeam_true() {
    Team firstTeam = new Team("teamBlue");
    Team secondTeam = new Team("teamRed");
    assertEquals(true, Team.all().contains(firstTeam));
    assertEquals(true, Team.all().contains(secondTeam));
  }

  @Test
  public void clear_emptiesAllTeamsFromList_0() {
    Team testTeam = new Team("teamBlue");
    Team.clear();
    assertEquals(Team.all().size(), 0);
  }

  @Test
  public void getId_teamsInstantiateWithAnId_1() {
    Team.clear();
    Team testTeam = new Team("teamBlue");
    assertEquals(1, testTeam.getId());
  }

  @Test
  public void find_returnsTeamWithSameId_secondTeam() {
    Team.clear();
    Team firstTeam = new Team("teamBlue");
    Team secondTeam = new Team("teamRed");
    assertEquals(Team.find(secondTeam.getId()), secondTeam);
  }

  @Test
  public void getMembers_initiallyReturnsEmptyList_ArrayList() {
    Team.clear();
    Team testTeam = new Team("teamBlue");
    assertEquals(0, testTeam.getMembers().size());
  }

  @Test
  public void addMember_addsMemberToList_true() {
    Team testTeam = new Team("teamBlue");
    Member testMember = new Member("Michael Dunlap", "Spark");
    testTeam.addMember(testMember);
    assertTrue(testTeam.getMembers().contains(testMember));
  }
}
