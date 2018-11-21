package org.rplsd.autocg;

import java.util.ArrayList;
import java.util.List;

public class Lecturers {
  private static Lecturers single_instance = null;
  private List<Lecturer> lecturers = new ArrayList<>();

  public static Lecturers getInstance() {
    if (single_instance == null)
      single_instance = new Lecturers();

    return single_instance;
  }

  public List<Lecturer> getLecturers() {
    return lecturers;
  }

  public ArrayList<ArrayList<Boolean>> getLecturerAvailabilityByName(String name) {
    for (Lecturer lecturer : lecturers) {
      if(lecturer.getName().equals(name)) {
        return lecturer.getAvailability();
      }
    }
    return null;
  }

  public void addLecturer(String name) {
    lecturers.add(new Lecturer(name));
  }

  public void addLecturer(String name, ArrayList<ArrayList<Boolean>> availability) {
    lecturers.add(new Lecturer(name, availability));
  }

  public class Lecturer {
    private String name;
    private ArrayList<ArrayList<Boolean>> availability;

    public Lecturer(String name) {
      this.name = name;
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

    public Lecturer(String name, ArrayList<ArrayList<Boolean>> availability) {
      this.name = name;
      this.availability = availability;
    }

    public ArrayList<ArrayList<Boolean>> getAvailability() {
      return availability;
    }

    public void setAvailability(ArrayList<ArrayList<Boolean>> availability) {
      this.availability = availability;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}