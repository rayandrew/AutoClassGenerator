package org.rplsd.autocg;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Schedules {
  private static Schedules single_instance = null;
  private Classrooms classrooms = Classrooms.getInstance();
  private Lecturers lecturers = Lecturers.getInstance();
  private Courses courses = Courses.getInstance();

  private ArrayList<ArrayList<Schedule>> schedules;

  private Schedules() {
    ArrayList<ArrayList<Schedule>> temp = new ArrayList<ArrayList<Schedule>>(Constant.WEEKDAYS.size());
    for (int i = 0; i < Constant.WEEKDAYS.size(); i++) {
      ArrayList<Schedule> _temp = new ArrayList<>(Constant.DAY_END - Constant.DAY_START);
      temp.add(_temp);
    }
    this.schedules = temp;
  }

  public static Schedules getInstance() {
    if (single_instance == null)
      single_instance = new Schedules();

    return single_instance;
  }

  public ArrayList<ArrayList<Schedule>> getSchedules() {
    return schedules;
  }


  public class Schedule {
    private Classrooms.Classroom classroom;
    private Lecturers.Lecturer lecturer;
    private Courses.Course course;

    public Schedule(Classrooms.Classroom classroom, Lecturers.Lecturer lecturer, Courses.Course course) {
      this.classroom = classroom;
      this.lecturer = lecturer;
      this.course = course;
    }
  }
}
