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

  }
}
