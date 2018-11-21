package org.rplsd.autocg.Listeners;

import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Parser.AutoCGParser.StmtContext;

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