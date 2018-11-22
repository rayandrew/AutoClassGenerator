package org.rplsd.autocg.scheduler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import org.rplsd.autocg.scheduler.Constraints.Constraint;

import java.util.ArrayList;

public class Schedules {
  private static Schedules single_instance = null;
  private transient Classrooms classrooms = Classrooms.getInstance();
  private transient Lecturers lecturers = Lecturers.getInstance();
  private transient Courses courses = Courses.getInstance();
  private transient Constraints constraints = Constraints.getInstance();

  private ArrayList<ArrayList<Schedule>> schedules;

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

              schedules.get(day).set(time, null);
              suitableClassroom.getAvailability().get(day).set(time, true);
              lecturer.getAvailability().get(day).set(time, true);
            }
          }
        }
      }
    }

    return false;
  }

  @Override
  public String toString() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
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
