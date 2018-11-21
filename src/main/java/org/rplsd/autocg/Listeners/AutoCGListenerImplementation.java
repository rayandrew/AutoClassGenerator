package org.rplsd.autocg.Listeners;

import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Parser.AutoCGParser.ParseContext;

public class AutoCGListenerImplementation extends AutoCGBaseListener {
  @Override
  public void enterParse(ParseContext ctx) {
    ctx.stmt_list().forEach(stmt -> stmt.enterRule(new StatementListListener()));
  }

  @Override
  public void exitParse(ParseContext ctx) {

  }
}