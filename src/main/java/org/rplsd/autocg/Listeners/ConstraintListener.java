package org.rplsd.autocg.listeners;

import org.rplsd.autocg.listeners.constraints.MaxLecturerHourListener;
import org.rplsd.autocg.listeners.constraints.RestrictedTimeListener;

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
  }
}
