package org.rplsd.autocg.scheduler;

import java.util.HashMap;
import java.util.List;

import org.rplsd.autocg.scheduler.interfaces.Constraint;

import java.util.ArrayList;

public class Schedules {
  private static Schedules single_instance = null;
  private Classrooms classrooms = Classrooms.getInstance();
  private Lecturers lecturers = Lecturers.getInstance();
  private Courses courses = Courses.getInstance();
  private List<Constraint> constraints = new ArrayList<>();

  private ArrayList<ArrayList<Schedule>> schedules;

  public static Schedules getInstance() {
    if (single_instance == null)
      single_instance = new Schedules();

    return single_instance;
  }

  public void registerConstraint(Constraint constraint) {
    this.constraints.add(constraint);
  }

  public void unregisterConstraint(Constraint constraint) {
    this.constraints.remove(constraint);
  }

  protected Boolean checkConstraints(int day, int time) {
    if (this.constraints.isEmpty())
      return true;

    // Notify each of the constraint in the list of registered constraint
    return this.constraints.stream().map((elem) -> elem.isPassed(single_instance, day, time))
        .reduce(Boolean::logicalAnd).get();
  }

  private Schedules() {
    ArrayList<ArrayList<Schedule>> temp = new ArrayList<ArrayList<Schedule>>(Constant.WEEKDAYS.size());
    for (int i = 0; i < Constant.WEEKDAYS.size(); i++) {
      ArrayList<Schedule> _temp = new ArrayList<>(Constant.DAY_END - Constant.DAY_START);
      for (int j = 0; j < Constant.DAY_END - Constant.DAY_START; j++) {
        _temp.add(null);
      }
      temp.add(_temp);
    }
    this.schedules = temp;
  }

  public ArrayList<ArrayList<Schedule>> getSchedules() {
    return schedules;
  }

  public List<Classrooms.Classroom> suitableClassroomsForCourse(Courses.Course course) {
    return classrooms.suitableClassroomsForCourse(course);
  }

  public boolean isLecturerAvailable(String day, int time) {
    return lecturers.isAvailableAt(day, time);
  }

  private boolean checkReqsAndConstraints(Classrooms.Classroom classroom, int day, int time) {
    return classroom.getAvailability().get(day).get(time) && checkConstraints(day, time);
  }

  public boolean generateSchedule(int courseIndex, int currentHour) {
    if (courseIndex > courses.getCourses().size())
      return true;

    Courses.Course course = courses.getCourses().get(courseIndex);
    List<Classrooms.Classroom> suitableClassroomsForCourse = suitableClassroomsForCourse(course);

    for (Classrooms.Classroom suitableClassroom : suitableClassroomsForCourse) {
      for (int day = 0; day < Constant.WEEKDAYS.size(); day++) {
        for (int time = 0; time <= Constant.DAY_END - Constant.DAY_START; time++) {
          if (checkReqsAndConstraints(suitableClassroom, day, time)) {
            // System.out.println("Class " + suitableClassroom.getName());
          }
        }
      }
    }

    return false;
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
