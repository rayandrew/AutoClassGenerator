package org.rplsd.autocg;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Parser.AutoCGParser.Any_nameContext;
import org.rplsd.autocg.Parser.AutoCGParser.Class_room_nameContext;
import org.rplsd.autocg.Parser.AutoCGParser.Config_defContext;
import org.rplsd.autocg.Parser.AutoCGParser.Config_nameContext;
import org.rplsd.autocg.Parser.AutoCGParser.Configuration_nameContext;
import org.rplsd.autocg.Parser.AutoCGParser.Create_class_room_stmtContext;
import org.rplsd.autocg.Parser.AutoCGParser.KeywordContext;
import org.rplsd.autocg.Parser.AutoCGParser.Value_nameContext;

public class AutoCGListenerImplementation extends AutoCGBaseListener {

  @Override
  public void visitTerminal(TerminalNode node) {

  }

  @Override
  public void visitErrorNode(ErrorNode node) {

  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {

  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {

  }

  @Override
  public void enterKeyword(KeywordContext ctx) {

  }

  @Override
  public void exitKeyword(KeywordContext ctx) {

  }

  @Override
  public void enterCreate_class_room_stmt(Create_class_room_stmtContext ctx) {
    System.out.println(ctx.class_room_name().any_name().children.get(0).toString());
    System.out.println(ctx.config_def().size());
    // for (int index = 0; index < ctx.config_def().size(); index++) {
    // System.out.println(ctx.config_def(index).config_name().children.size());
    // for (int idx = 0; idx < ctx.config_def(index).config_name().children.size();
    // idx++) {
    // System.out.println(ctx.config_def(index).config_name().getChild(idx).toString());
    // System.out.println(ctx.config_def(index).value_name().getChild(idx).toString());
    // }
    // }
    // ctx.config_def()
    // ctx.config_def().forEach(config_def -> {
    // config_def.config_name().children.forEach(config ->
    // System.out.println(config.getChild(0).toString()));
    // config_def.value_name().children.forEach(config ->
    // System.out.println(config.getChild(0).toString()));
    // });
    System.out.print(ctx.toStringTree());
  }

  @Override
  public void exitCreate_class_room_stmt(Create_class_room_stmtContext ctx) {

  }

  @Override
  public void enterConfig_def(Config_defContext ctx) {

  }

  @Override
  public void exitConfig_def(Config_defContext ctx) {

  }

  @Override
  public void enterAny_name(Any_nameContext ctx) {
    System.out.println(ctx.toString());
  }

  @Override
  public void exitAny_name(Any_nameContext ctx) {

  }

  @Override
  public void enterClass_room_name(Class_room_nameContext ctx) {

  }

  @Override
  public void exitClass_room_name(Class_room_nameContext ctx) {

  }

  @Override
  public void enterConfiguration_name(Configuration_nameContext ctx) {

  }

  @Override
  public void exitConfiguration_name(Configuration_nameContext ctx) {

  }

  @Override
  public void enterConfig_name(Config_nameContext ctx) {

  }

  @Override
  public void exitConfig_name(Config_nameContext ctx) {

  }

  @Override
  public void enterValue_name(Value_nameContext ctx) {

  }

  @Override
  public void exitValue_name(Value_nameContext ctx) {

  }

}