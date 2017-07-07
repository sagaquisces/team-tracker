public class Member {
  private String mName;
  private String mOrganization;

  public Member(String name, String organization) {
    mName = name;
    mOrganization = organization;
  }

  public String getName() {
    return mName;
  }

  public String getOrganization() {
    return mOrganization;
  }
}
