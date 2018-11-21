package org.rplsd.autocg.Listeners;

import java.util.HashMap;

import org.rplsd.autocg.Courses;
import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Parser.AutoCGParser.Create_course_stmtContext;

public class CourseListener extends AutoCGBaseListener {
  @Override
  public void enterCreate_course_stmt(Create_course_stmtContext ctx) {
    Courses courses = Courses.getInstance();

    String name = ctx.course_name().any_name().children.get(0).toString();
    HashMap<String, Integer> facilities = new HashMap<>();
    int duration = 0;

    for (int index = 0; index < ctx.config_def().size(); index++) {
      if (ctx.config_def(index).config_name().getChild(0).getChild(0).toString().toLowerCase().equals("duration")) {
        duration = Integer.parseInt(ctx.config_def(index).value_name().getChild(0).getChild(0).toString());
      } else {
        facilities.put(ctx.config_def(index).config_name().getChild(0).getChild(0).toString(),
            Integer.parseInt(ctx.config_def(index).value_name().getChild(0).getChild(0).toString()));
      }
    }

    courses.addCourse(name, facilities, duration);
  }

  @Override
  public void exitCreate_course_stmt(Create_course_stmtContext ctx) {

  }
}