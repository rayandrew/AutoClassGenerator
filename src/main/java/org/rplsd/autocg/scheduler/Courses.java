package org.rplsd.autocg.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

public class Courses {
  private static Courses single_instance = null;
  private ArrayList<Course> courses = new ArrayList<>();

  public static Courses getInstance() {
    if (single_instance == null)
      single_instance = new Courses();

    return single_instance;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  public void addCourse(String courseName, HashMap<String, Integer> requirements, int duration) {
    courses.add(new Course(courseName, requirements, duration));
  }

  public Course getCourseByName(String name) {
    for (Course course : courses) {
      if (course.getCourseName().equals(name)) {
        return course;
      }
    }

    return null;
  }

  public class Course {
    @Expose
    private String courseName;
    @Expose
    private HashMap<String, Integer> requirements;
    private int duration;

    public Course(String courseName, HashMap<String, Integer> requirements, int duration) {
      this.courseName = courseName;
      this.requirements = requirements;
      this.duration = duration;
    }

    public String getCourseName() {
      return courseName;
    }

    public void setCourseName(String courseName) {
      this.courseName = courseName;
    }

    public HashMap<String, Integer> getRequirements() {
      return requirements;
    }

    public void setRequirements(HashMap<String, Integer> requirements) {
      this.requirements = requirements;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
    }
  }
}