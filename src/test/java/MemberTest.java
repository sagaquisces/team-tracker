import org.junit.*;
import static org.junit.Assert.*;

public class MemberTest {

  @Test
  public void Member_instantiatesCorrectly_true() {
    Member myMember = new Member("Michael Dunlap", "Spark");
    assertEquals(true, myMember instanceof Member);
  }

  @Test
  public void Member_instantiatesWithName_String() {
    Member myMember = new Member("Michael Dunlap", "Spark");
    assertEquals("Michael Dunlap", myMember.getName());
  }

  @Test
  public void Member_instantiatesWithOrganization_String() {
    Member myMember = new Member("Michael Dunlap", "Spark");
    assertEquals("Spark", myMember.getOrganization());
  }

  @Test
  public void all_returnsAllInstancesOfMember_true() {
    Member firstMember = new Member("Michael Dunlap", "Spark");
    Member secondMember = new Member("Trey Walker", "Independence");
    assertEquals(true, Member.all().contains(firstMember));
    assertEquals(true, Member.all().contains(secondMember));
  }

  @Test
  public void clear_emptiesAllMembersFromArrayList_0() {
    Member myMember = new Member("Michael Dunlap", "Spark");
    Member.clear();
    assertEquals(0, Member.all().size());
  }

  @Test
  public void getId_membersInstantiateWithAnID_1() {
    Member.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!
    Member myMember = new Member("Michael Dunlap", "Spark");
    assertEquals(1, myMember.getId());
  }

  @Test
  public void find_returnsMemberWithSameId_secondTask() {
    Member firstMember = new Member("Michael Dunlap", "Spark");
    Member secondMember = new Member("Trey Walker", "Independence");
    assertEquals(Member.find(secondMember.getId()), secondMember);
  }

  @Test
  public void setTeam_setsMemberWithTeamName_blueTeam() {
    Member testMember = new Member("Michael Dunlap", "Spark");
    testMember.setTeamName("blueTeam");
    assertEquals("blueTeam", testMember.getTeamName());
  }

}
