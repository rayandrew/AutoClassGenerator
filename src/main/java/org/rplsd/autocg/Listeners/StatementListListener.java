package org.rplsd.autocg.Listeners;

import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Parser.AutoCGParser.Stmt_listContext;

public class StatementListListener extends AutoCGBaseListener {
  @Override
  public void enterStmt_list(Stmt_listContext ctx) {
    ctx.stmt().forEach(stmt -> stmt.enterRule(new StatementListener()));
  }

  @Override
  public void exitStmt_list(Stmt_listContext ctx) {

  }
}