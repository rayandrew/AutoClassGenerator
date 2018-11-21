package org.rplsd.autocg;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Schedules {
  private static Schedules single_instance = null;
  private List<Schedule> schedules = new ArrayList<>();

  public static Schedules getInstance() {
    if (single_instance == null)
      single_instance = new Schedules();

    return single_instance;
  }

  public List<Schedule> getCourses() {
    return schedules;
  }

  public class Schedule {
    private Classrooms classrooms = Classrooms.getInstance();
    private Lecturers lecturers = Lecturers.getInstance();
    private Courses courses = Courses.getInstance();

    // public Schedule(ArrayList<Classroom> classrooms, ArrayList<Lecturer>
    // lecturers, ArrayList<Course> courses) {
    // this.classrooms = classrooms;
    // this.lecturers = lecturers;
    // this.courses = courses;
    // }
  }
}
