import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("mostRecentMember", request.session().attribute("mostRecentMember"));
      model.put("members", request.session().attribute("members"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("members/new", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("template", "templates/member-form.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/members", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("members", Member.all());
     model.put("template", "templates/members.vtl");
     return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/members", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      ArrayList<Member> members = request.session().attribute("members");
      if (members == null) {
        members = new ArrayList<Member>();
        request.session().attribute("members", members);
      }

      String name = request.queryParams("name");
      String organization = request.queryParams("organization");

      Member newMember = new Member(name, organization);
      members.add(newMember);
      request.session().attribute("mostRecentMember", newMember);

      model.put("mostRecentMember", newMember);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/teams/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/team-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/teams", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      List<Team> allTeams = Team.all();
      boolean isDuplicate = false;
      for(Team t:allTeams) {
        if (t.getName().equals(name)) {
          isDuplicate = true;
        }
      }

      if (isDuplicate) {
        model.put("name",name);
        model.put("template","templates/team-duplicate.vtl");
      } else {
        Team newTeam = new Team(name);
        request.session().attribute("mostRecentTeam", newTeam);
        model.put("mostRecentTeam", newTeam);
        model.put("template", "templates/team-success.vtl");
      }

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/teams", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("teams", Team.all());
      model.put("template", "templates/teams.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/teams/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Team team = Team.find(Integer.parseInt(request.params(":id")));
      model.put("team", team);
      model.put("template", "templates/team.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
