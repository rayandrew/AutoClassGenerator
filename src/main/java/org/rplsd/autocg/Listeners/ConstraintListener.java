package org.rplsd.autocg.listeners;

import java.util.HashMap;

import org.rplsd.autocg.scheduler.Courses;

import org.rplsd.autocg.listeners.constraints.MaxLecturerHourListener;
import org.rplsd.autocg.listeners.constraints.RestrictedTimeListener;;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Create_constraint_stmtContext;

public class ConstraintListener extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterCreate_constraint_stmt(Create_constraint_stmtContext ctx) {
    if (ctx.max_hour_def() != null) {
      ctx.max_hour_def().enterRule(new MaxLecturerHourListener());
    }
    if (ctx.restricted_def() != null) {
      ctx.restricted_def().enterRule(new RestrictedTimeListener());
    }

    // System.out.println(ctx.constraint_def().max_hour_value().getChild(0));

    // System.out.println(ctx.restricted_def().restricted_val_def().toString());

    // for (int i = 0;i < ctx.restricted_def().restricted_val_def().size();i++) {
    // for (int j = 0;j <
    // ctx.restricted_def().restricted_val_def().value_restricted().size();j++) {
    // System.out.println(ctx.restricted_def().restricted_val_def(i).value_restricted(j));
    // }
    // }
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void exitCreate_constraint_stmt(Create_constraint_stmtContext ctx) {

  }
}
