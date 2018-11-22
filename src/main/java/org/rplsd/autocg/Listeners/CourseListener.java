package org.rplsd.autocg.listeners;

import java.util.HashMap;

import org.rplsd.autocg.scheduler.Courses;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Create_course_stmtContext;

public class CourseListener extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterCreate_course_stmt(Create_course_stmtContext ctx) {
    Courses courses = Courses.getInstance();

    String name = ctx.course_name().any_name().children.get(0).toString();
    HashMap<String, Integer> requirements = new HashMap<>();
    int duration = 0;

    for (int index = 0; index < ctx.config_def().size(); index++) {
      if (ctx.config_def(index).config_name().getChild(0).getChild(0).toString().toLowerCase().equals("duration")) {
        duration = Integer.parseInt(ctx.config_def(index).value_name().getChild(0).getChild(0).toString());
      } else {
        requirements.put(ctx.config_def(index).config_name().getChild(0).getChild(0).toString().toUpperCase(),
            Integer.parseInt(ctx.config_def(index).value_name().getChild(0).getChild(0).toString()));
      }
    }

    courses.addCourse(name, requirements, duration);
  }
}