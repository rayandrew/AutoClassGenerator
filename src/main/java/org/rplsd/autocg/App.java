package org.rplsd.autocg;

import java.util.ArrayList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.rplsd.autocg.parser.*;
import org.rplsd.autocg.scheduler.*;
import org.rplsd.autocg.listeners.AutoCGListenerImplementation;
import org.rplsd.autocg.scheduler.constraints.*;

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
      parser.parse().enterRule(listener);

      // Classrooms classrooms = Classrooms.getInstance();
      // System.out.println(classrooms.getClassroomByName("7602").getFacilities().toString());

      // Courses courses = Courses.getInstance();
      // System.out.println(courses.getCourseByName("IF4070").getRequirements().toString());

      // Lecturers lecturers = Lecturers.getInstance();
      // System.out.println(lecturers.getLecturerByName("IMAM").getName());

      Schedules schedules = Schedules.getInstance();
      schedules.registerConstraint(new MaxLecturerHour(10));
      schedules.registerConstraint(new RestrictedTime());

      // System.out.println(schedules.suitableClassroomsForCourse(courses.getCourseByName("IF4070")).size());
      // System.out.println(schedules.isLecturerAvailable("MONDAY", 10));
      boolean status = schedules.generateSchedule(0, 0);
      System.out.println("\nRESULTTTTT");
      for (ArrayList<Schedules.Schedule> schedule : schedules.getSchedules()) {
        System.out.println(schedule.toString());
      }
      System.out.println(status);
    } catch (Exception e) {
      if (e.getMessage() != null) {
        System.err.println(e.getMessage());
      } else {
        e.printStackTrace();
      }
    }
  }
}
