package org.rplsd.autocg.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class Classrooms {
  private static Classrooms single_instance = null;
  private ArrayList<Classroom> classrooms = new ArrayList<Classroom>() {
    public boolean add(Classroom mt) {
      int index = Collections.binarySearch(this, mt, new Comparator<Classroom>() {
        public int compare(Classroom u1, Classroom u2) {
          Integer capacityU1 = u1.getFacilities().get(new String("CAPACITY"));
          Integer capacityU2 = u2.getFacilities().get(new String("CAPACITY"));

          if (capacityU1 == null || capacityU2 == null) {
            return 0;
          } else {
            return capacityU1.compareTo(capacityU2);
          }
        }
      });

      if (index < 0)
        index = ~index;

      super.add(index, mt);
      return true;
    }
  };

  /**
   * @return the single_instance
   */
  public static Classrooms getInstance() {
    if (single_instance == null)
      single_instance = new Classrooms();

    return single_instance;
  }

  /**
   * @return the classrooms
   */
  public ArrayList<Classroom> getClassrooms() {
    return classrooms;
  }

  public ArrayList<ArrayList<Boolean>> getClassroomAvailabilityByName(String name) {
    Classroom classroom = getClassroomByName(name);

    if (classroom != null)
      return classroom.getAvailability();

    return null;
  }

  public void addClassroom(String name, HashMap<String, Integer> facilities) {
    classrooms.add(new Classroom(name, facilities));
  }

  public void addClassroom(String name, HashMap<String, Integer> facilities,
      ArrayList<ArrayList<Boolean>> availability) {
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

  public ArrayList<Classroom> suitableClassroomsForCourse(Courses.Course course) {
    ArrayList<Classroom> suitableClassrooms = new ArrayList<>();

    for (Classroom classroom : classrooms) {
      boolean cond = true;
      for (HashMap.Entry<String, Integer> entryCourseReqs : course.getRequirements().entrySet()) {
        Integer facilitiesValue = classroom.getFacilities().get(entryCourseReqs.getKey());
        if (facilitiesValue == null || facilitiesValue < entryCourseReqs.getValue()) {
          cond = false;
          break;
        } else {
          cond = true;
        }
      }

      if (cond) {
        suitableClassrooms.add(classroom);
      }
    }

    return suitableClassrooms;
  }

  public class Classroom {
    @Expose
    String name;
    @Expose
    private HashMap<String, Integer> facilities;
    private transient ArrayList<ArrayList<Boolean>> availability;

    public Classroom(String name, HashMap<String, Integer> facilities) {
      this.name = name;
      this.facilities = facilities;
      ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>(Constants.WEEKDAYS.size());

      for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
        ArrayList<Boolean> _temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);

        for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
          _temp.add(true);
        }

        temp.add(_temp);
      }

      this.availability = temp;
    }

    public Classroom(String name, HashMap<String, Integer> facilities, ArrayList<ArrayList<Boolean>> availability) {
      this.name = name;
      this.facilities = facilities;
      this.availability = availability;
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