package org.rplsd.autocg.scheduler;

import org.rplsd.autocg.Util.Pair;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class SchedulePreference {
  private static SchedulePreference single_instance = null;
  private HashMap<String, List<Pair<Integer, Integer>>> lecturerPreferredTime;

  public static SchedulePreference getInstance() {
    if (single_instance == null)
      single_instance = new SchedulePreference();

    return single_instance;
  }

  private SchedulePreference() {
    lecturerPreferredTime = new HashMap<>();
  }

  public HashMap<String, List<Pair<Integer, Integer>>> getLecturerPreferredTime() {
    return lecturerPreferredTime;
  }

  public void setLecturerPreferredTime(HashMap<String, List<Pair<Integer, Integer>>> lecturerPreferredTime) {
    this.lecturerPreferredTime = lecturerPreferredTime;
  }

  public void addLecturerPreferredTime(String name, int day, int hour) {
    List<Pair<Integer, Integer>> lecturerPreferredTimeList = lecturerPreferredTime.get(name);
    if (lecturerPreferredTimeList == null) {
      lecturerPreferredTimeList = new ArrayList<Pair<Integer, Integer>>();
    }
    lecturerPreferredTimeList.add(new Pair<>(day, hour));
    lecturerPreferredTime.put(name, lecturerPreferredTimeList);
  }
}