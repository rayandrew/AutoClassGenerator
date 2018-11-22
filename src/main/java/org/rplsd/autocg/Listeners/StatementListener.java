package org.rplsd.autocg.listeners;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.StmtContext;

public class StatementListener extends AutoCGBaseListener {
  @Override
  public void enterStmt(StmtContext ctx) {
    if (ctx.create_class_room_stmt() != null) {
      ctx.create_class_room_stmt().enterRule(new ClassroomListener());
    }
    if (ctx.create_course_stmt() != null) {
      ctx.create_course_stmt().enterRule(new CourseListener());
    }
    if (ctx.create_lecture_stmt() != null) {
      ctx.create_lecture_stmt().enterRule(new LectureListener());
    }
  }

  @Override
  public void exitStmt(StmtContext ctx) {

  }
}