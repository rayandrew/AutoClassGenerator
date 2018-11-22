package org.rplsd.autocg.listeners;

import java.util.HashMap;

import org.rplsd.autocg.scheduler.Classrooms;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Create_class_room_stmtContext;

public class ClassroomListener extends AutoCGBaseListener {
  @Override
  public void enterCreate_class_room_stmt(Create_class_room_stmtContext ctx) {
    Classrooms classrooms = Classrooms.getInstance();

    String name = ctx.class_room_name().any_name().children.get(0).toString();
    HashMap<String, Integer> facilities = new HashMap<>();

    for (int index = 0; index < ctx.config_def().size(); index++) {
      facilities.put(ctx.config_def(index).config_name().getChild(0).getChild(0).toString().toUpperCase(),
          Integer.parseInt(ctx.config_def(index).value_name().getChild(0).getChild(0).toString()));
    }

    classrooms.addClassroom(name, facilities);
  }

  @Override
  public void exitCreate_class_room_stmt(Create_class_room_stmtContext ctx) {

  }
}