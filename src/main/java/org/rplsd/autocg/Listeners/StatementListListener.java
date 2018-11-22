package org.rplsd.autocg.listeners;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Stmt_listContext;

public class StatementListListener extends AutoCGBaseListener {
  @Override
  public void enterStmt_list(Stmt_listContext ctx) {
    ctx.stmt().forEach(stmt -> stmt.enterRule(new StatementListener()));
  }

  @Override
  public void exitStmt_list(Stmt_listContext ctx) {

  }
}