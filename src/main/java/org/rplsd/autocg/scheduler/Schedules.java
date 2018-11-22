package org.rplsd.autocg.scheduler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.List;

import org.rplsd.autocg.scheduler.Constraints.Constraint;

import java.util.ArrayList;

public class Schedules {
  private static Schedules single_instance = null;

  private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

  private Classrooms classrooms = Classrooms.getInstance();
  private Lecturers lecturers = Lecturers.getInstance();
  private Courses courses = Courses.getInstance();
  private Constraints constraints = Constraints.getInstance();

  @Expose
  ArrayList<ArrayList<Schedule>> schedules;

  public static Schedules getInstance() {
    if (single_instance == null)
      single_instance = new Schedules();

    return single_instance;
  }

  /**
   * @return the classrooms
   */
  public Classrooms getClassrooms() {
    return classrooms;
  }

  /**
   * @return the courses
   */
  public Courses getCourses() {
    return courses;
  }

  /**
   * @return the constraints
   */
  public Constraints getConstraints() {
    return constraints;
  }

  /**
   * @return the lecturers
   */
  public Lecturers getLecturers() {
    return lecturers;
  }

  public void registerConstraint(Constraint constraint) {
    constraints.addConstraint(constraint);
  }

  public void unregisterConstraint(Constraint constraint) {
    constraints.removeConstraint(constraint);
  }

  protected Boolean checkConstraints(int day, int time) {
    return constraints.checkConstraints(single_instance, day, time);
  }

  private Schedules() {
    ArrayList<ArrayList<Schedule>> temp = new ArrayList<>(Constants.WEEKDAYS.size());
    for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
      ArrayList<Schedule> _temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);
      for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
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

  private boolean checkReqsAndConstraints(Classrooms.Classroom classroom, int day, int time) {
    return classroom.getAvailability().get(day).get(time) && checkConstraints(day, time);
  }

  public boolean generateSchedule(int courseIndex, int currentHour) {
    if (courseIndex >= courses.getCourses().size())
      return true;

    Courses.Course course = courses.getCourses().get(courseIndex);
    List<Classrooms.Classroom> suitableClassroomsForCourse = suitableClassroomsForCourse(course);

    // System.out.println(courseIndex + " " + currentHour + " " +
    // suitableClassroomsForCourse.size());

    for (Classrooms.Classroom suitableClassroom : suitableClassroomsForCourse) {
      // System.out.println(suitableClassroom.getName());
      for (int day = 0; day < Constants.WEEKDAYS.size(); day++) {
        for (int time = 0; time < Constants.DAY_END - Constants.DAY_START; time++) {
          if (checkReqsAndConstraints(suitableClassroom, day, time)) {
            Lecturers.Lecturer lecturer = lecturers.getFirstAvailableLecturer(day, time);

            if (lecturer != null) {
              Schedule schedule = new Schedule(suitableClassroom, lecturer, course);
              lecturer.getAvailability().get(day).set(time, false);
              schedules.get(day).set(time, schedule);
              suitableClassroom.getAvailability().get(day).set(time, false);

              // for (ArrayList<Boolean> cek : lecturer.getAvailability()) {
              // System.out.println(cek.toString());
              // }

              int nextHour = currentHour < course.getDuration() - 1 ? currentHour + 1 : 0;
              int nextClassRequirementIndex = nextHour == 0 ? courseIndex + 1 : courseIndex;

              if (generateSchedule(nextClassRequirementIndex, nextHour))
                return true;

              // schedules.get(day).set(time, null);
              suitableClassroom.getAvailability().get(day).set(time, true);
              lecturer.getAvailability().get(day).set(time, true);
            }
          }
        }
      }
    }

    return false;
  }

  public void saveJSON(String filePath) {
    try {
      File file = new File(filePath);
      file.getParentFile().mkdirs();
      FileWriter writer = new FileWriter(file);
      gson.toJson(this, writer);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return gson.toJson(this);
  }

  public void outputSchedule() {
    System.out.println(
        String.format("%10s %15s %15s %15s %15s %15s", "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

    for (int time = 0; time < Constants.DAY_END - Constants.DAY_START; time++) {
      StringBuilder str = new StringBuilder();
      str.append(String.format("%10s", time + Constants.DAY_START + ".00"));
      for (int day = 0; day < Constants.WEEKDAYS.size(); day++) {
        Schedule schedule = getSchedules().get(day).get(time);
        if (schedule != null) {
          str.append(String.format("%2s [%2s][%2s] %2s %5s", " ", schedule.getClassroom().getName(),
              schedule.getCourse().getCourseName(), schedule.getLecturer().getName(), " "));
        } else {
          str.append(String.format("%10s %13s", " ", " "));
        }
      }
      System.out.println(str.toString());
    }
  }

  // @Override
  // public String toString() {
  // StringBuilder str = new StringBuilder();
  // str.append(String.format("%10s %15s %15s %15s %15s %15s\n", "Time", "Monday",
  // "Tuesday", "Wednesday", "Thursday",
  // "Friday"));

  // for (int time = 0; time < Constants.DAY_END - Constants.DAY_START; time++) {
  // str.append(String.format("%10s", time + Constants.DAY_START + ".00"));
  // for (int day = 0; day < Constants.WEEKDAYS.size(); day++) {
  // Schedule schedule = schedules.get(day).get(time);
  // if (schedule != null) {
  // str.append(String.format("%2s [%2s][%2s] %2s %5s", " ",
  // schedule.getClassroom().getName(),
  // schedule.getCourse().getCourseName(), schedule.getLecturer().getName(), ""));
  // } else {
  // str.append(String.format("%10s %13s", " ", " "));
  // }
  // }
  // str.append(str.toString() + "\n");
  // }

  // return str.toString();
  // }

  public class Schedule {
    @Expose
    Classrooms.Classroom classroom;
    @Expose
    Lecturers.Lecturer lecturer;
    @Expose
    Courses.Course course;

    public Schedule(Classrooms.Classroom classroom, Lecturers.Lecturer lecturer, Courses.Course course) {
      this.classroom = classroom;
      this.lecturer = lecturer;
      this.course = course;
    }

    public Classrooms.Classroom getClassroom() {
      return classroom;
    }

    public void setClassroom(Classrooms.Classroom classroom) {
      this.classroom = classroom;
    }

    public Lecturers.Lecturer getLecturer() {
      return lecturer;
    }

    public void setLecturer(Lecturers.Lecturer lecturer) {
      this.lecturer = lecturer;
    }

    public Courses.Course getCourse() {
      return course;
    }

    public void setCourse(Courses.Course course) {
      this.course = course;
    }

    @Override
    public String toString() {
      if (classroom == null && lecturer == null && course == null) {
        return "Empty";
      }

      return "[Classroom " + classroom.getName() + "][" + course.getCourseName() + "] by " + lecturer.getName();
    }
  }
}
