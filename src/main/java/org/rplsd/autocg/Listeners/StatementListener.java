package org.rplsd.autocg.listeners;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.StmtContext;

public class StatementListener extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
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
    if (ctx.create_constraint_stmt() != null) {
      ctx.create_constraint_stmt().enterRule(new ConstraintListener());
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void exitStmt(StmtContext ctx) {

  }
}