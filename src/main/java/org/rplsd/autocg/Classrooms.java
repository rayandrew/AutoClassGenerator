package org.rplsd.autocg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Classrooms {
  private static Classrooms single_instance = null;
  private List<Classroom> classrooms = new ArrayList<>();

  public static Classrooms getInstance() {
    if (single_instance == null)
      single_instance = new Classrooms();

    return single_instance;
  }

  public List<Classroom> getClassrooms() {
    return classrooms;
  }

  public void addClassroom(String name, HashMap<String, Integer> facilities) {
    classrooms.add(new Classroom(name, facilities));
  }

  public Classroom getClassroomByName(String name) {
    for (Classroom classroom : classrooms) {
      if (classroom.getName().equals(name)) {
        return classroom;
      }
    }

    return null;
  }

  public class Classroom {
    private String name;
    private HashMap<String, Integer> facilities;

    public Classroom(String name, HashMap<String, Integer> facilities) {
      this.name = name;
      this.facilities = facilities;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public HashMap<String, Integer> getFacilities() {
      return facilities;
    }

    public void setFacilities(HashMap<String, Integer> facilities) {
      this.facilities = facilities;
    }
  }
}