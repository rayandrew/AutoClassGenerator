package org.rplsd.autocg;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Date;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.rplsd.autocg.parser.*;
import org.rplsd.autocg.scheduler.*;
import org.rplsd.autocg.scheduler.Courses.Course;
import org.rplsd.autocg.scheduler.Schedules.Schedule;
import org.rplsd.autocg.listeners.AutoCGListenerImplementation;
// import org.rplsd.autocg.scheduler.constraints.*;

public class App {
  public static void main(String[] args) {
    try {
      AutoCGLexer lexer = new AutoCGLexer(CharStreams.fromFileName("./src/main/autocg/test.autocg"));
      AutoCGParser parser = new AutoCGParser(new CommonTokenStream(lexer));
      AutoCGListenerImplementation listener = new AutoCGListenerImplementation();
      parser.parse().enterRule(listener);

      Schedules schedules = Schedules.getInstance();
      boolean status = schedules.generateSchedule(0, 0);

      if (status) {
        System.out.println("\nRESULT");

        schedules.outputSchedule();
        schedules.saveJSON("./result/" + new Date().toString() + ".json");

        // for (ArrayList<Schedules.Schedule> schedule : schedules.getSchedules()) {
        // System.out.println(schedule.toString());
        // }
      } else {
        System.out.println("Schedule cannot be generated");
      }
    } catch (Exception e) {
      if (e.getMessage() != null) {
        System.err.println(e.getMessage());
      } else {
        e.printStackTrace();
      }
    }
  }
}
