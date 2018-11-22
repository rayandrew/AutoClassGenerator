package org.rplsd.autocg.listeners;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.ParseContext;

public class AutoCGListenerImplementation extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterParse(ParseContext ctx) {
    ctx.stmt_list().forEach(stmt -> stmt.enterRule(new StatementListListener()));
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void exitParse(ParseContext ctx) {

  }
}