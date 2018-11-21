package org.rplsd.autocg;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
// import org.antlr.v4.runtime.tree.ParseTree;
// import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.rplsd.autocg.Parser.*;
import org.rplsd.autocg.Listeners.AutoCGListenerImplementation;

// import java.util.HashMap;
// import java.util.List;
// import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    // HashMap<String, Integer> facilities = new HashMap<String, Integer>();
    // facilities.put("ac", 1);
    // facilities.put("capacity", 30);
    // facilities.put("lcd", 1);
    // Classroom classroom = new Classroom("7602", facilities);

    // List<String> lecturers = new ArrayList<String>();
    // lecturers.add("Imam");
    // lecturers.add("Catur");
    // Course course = new Course("PAT", facilities, 2, lecturers);

    // ArrayList<ArrayList<Boolean>> availability = new
    // ArrayList<ArrayList<Boolean>>();
    // ArrayList<Boolean> perday = new ArrayList<Boolean>();
    // perday.add(true);
    // perday.add(false);

    // availability.add(perday);
    // availability.add(perday);

    // Lecturer lecturer = new Lecturer("Imam", availability);

    // System.out.println(lecturer.getAvailability());
    // System.out.println(lecturer.getName());
    try {
      AutoCGLexer lexer = new AutoCGLexer(CharStreams.fromFileName("./src/main/autocg/test.autocg"));
      AutoCGParser parser = new AutoCGParser(new CommonTokenStream(lexer));
      AutoCGListenerImplementation listener = new AutoCGListenerImplementation();
      // ParseTree parseTree = parser.parse();
      // ParseTreeWalker.DEFAULT.walk(listener, parseTree);
      parser.parse().enterRule(listener);
      // Classrooms classrooms = Classrooms.getInstance();
      // System.out.println(classrooms.getClassroomByName("7602").getFacilities().toString());
      // Courses courses = Courses.getInstance();
      // System.out.println(courses.getCourseByName("IF4070").getDuration());
    } catch (Exception e) {
      if (e.getMessage() != null) {
        System.err.println(e.getMessage());
      } else {
        e.printStackTrace();
      }
    }
  }
}
