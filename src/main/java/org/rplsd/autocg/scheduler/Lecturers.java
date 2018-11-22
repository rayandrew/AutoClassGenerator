package org.rplsd.autocg.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

public class Lecturers {
  private static Lecturers single_instance = null;
  private ArrayList<Lecturer> lecturers = new ArrayList<>();
  private HashMap<String, ArrayList<ArrayList<Boolean>>> availability = new HashMap<>();

  public static Lecturers getInstance() {
    if (single_instance == null)
      single_instance = new Lecturers();

    return single_instance;
  }

  public ArrayList<Lecturer> getLecturers() {
    return lecturers;
  }

  public void addLecturer(String name) {
    lecturers.add(new Lecturer(name));
  }

  public void addLecturer(String name, ArrayList<ArrayList<Boolean>> availability) {
    lecturers.add(new Lecturer(name, availability));
  }

  public Lecturer getLecturerByName(String name) {
    for (Lecturer lecturer : lecturers) {
      if (lecturer.getName().equals(name)) {
        return lecturer;
      }
    }

    return null;
  }

  public ArrayList<ArrayList<Boolean>> getLecturerAvailabilityByName(String name) {
    Lecturer lecturer = getLecturerByName(name);

    if (lecturer != null)
      return lecturer.getAvailability();

    return null;
  }

  public Lecturer getFirstAvailableLecturer(int day, int time) {
    for (Lecturer lecturer : lecturers) {
      if (lecturer.getAvailability().get(day).get(time)) {
        return lecturer;
      }
    }

    return null;
  }

  public class Lecturer {
    private String name;
    private transient ArrayList<ArrayList<Boolean>> availability;

    public Lecturer(String name) {
      this.name = name;
      ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>(Constants.WEEKDAYS.size());

      for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
        ArrayList<Boolean> _temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);

        for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
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