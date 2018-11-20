package org.rplsd.autocg;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.rplsd.autocg.Parser.*;

public class App {
  public static void main(String[] args) {
    try {
      AutoCGLexer lexer = new AutoCGLexer(CharStreams.fromFileName("./src/main/autocg/test.autocg"));
      AutoCGParser parser = new AutoCGParser(new CommonTokenStream(lexer));
      AutoCGListenerImplementation listener = new AutoCGListenerImplementation();
      parser.create_class_room_stmt().enterRule(listener);
    } catch (Exception e) {
      if (e.getMessage() != null) {
        System.err.println(e.getMessage());
      } else {
        e.printStackTrace();
      }
    }
  }
}
