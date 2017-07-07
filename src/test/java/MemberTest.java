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

}
