package org.rplsd.autocg.listeners.constraints;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Restricted_defContext;

public class RestrictedTimeListener extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterRestricted_def(Restricted_defContext ctx) {
    System.out.println(ctx.children.get(0).toString());
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void exitRestricted_def(Restricted_defContext ctx) {
  }
}
