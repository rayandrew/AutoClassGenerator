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

  public ArrayList<ArrayList<Boolean>> getClassroomAvailabilityByName(String name) {
    for (Classroom classroom : classrooms) {
      if(classroom.getName().equals(name)) {
        return classroom.getAvailability();
      }
    }
    return null;
  }

  public void addClassroom(String name, HashMap<String, Integer> facilities) {
    classrooms.add(new Classroom(name, facilities));
  }
  
  public void addClassroom(String name, HashMap<String, Integer> facilities,  ArrayList<ArrayList<Boolean>> availability) {
    classrooms.add(new Classroom(name, facilities, availability));
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
    private ArrayList<ArrayList<Boolean>> availability;


    public Classroom(String name, HashMap<String, Integer> facilities) {
      this.name = name;
      this.facilities = facilities;
      ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>(Constant.WEEKDAYS.size());

      for (int i = 0; i < Constant.WEEKDAYS.size(); i++) {
        ArrayList<Boolean> _temp = new ArrayList<>(Constant.DAY_END - Constant.DAY_START);

        for (int j = 0; j <= Constant.DAY_END - Constant.DAY_START; j++) {
          _temp.add(false);
        }

        temp.add(_temp);
      }

      this.availability = temp;
    }

    public Classroom(String name, HashMap<String, Integer> facilities, ArrayList<ArrayList<Boolean>> availability) {
      this.name = name;
      this.facilities = facilities;
      ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>(Constant.WEEKDAYS.size());

      for (int i = 0; i < Constant.WEEKDAYS.size(); i++) {
        ArrayList<Boolean> _temp = new ArrayList<>(Constant.DAY_END - Constant.DAY_START);

        for (int j = 0; j <= Constant.DAY_END - Constant.DAY_START; j++) {
          _temp.add(false);
        }

        temp.add(_temp);
      }

      this.availability = temp;
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

    public ArrayList<ArrayList<Boolean>> getAvailability() {
      return availability;
    }

    public void setAvailability(ArrayList<ArrayList<Boolean>> availability) {
      this.availability = availability;
    }
  }
}