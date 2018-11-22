package org.rplsd.autocg.scheduler;

import org.rplsd.autocg.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class ScheduleConstraint {
  private static ScheduleConstraint single_instance = null;
  private Set<Pair<Integer, Integer>> restrictedTime;
  private int maxLecturerHour;

  public static ScheduleConstraint getInstance() {
    if (single_instance == null)
      single_instance = new ScheduleConstraint();

    return single_instance;
  }

  private ScheduleConstraint() {
    restrictedTime = new HashSet<>();
    maxLecturerHour = 10;
  }

  private ScheduleConstraint(Set<Pair<Integer, Integer>> restrictedTime, int maxLecturerHour) {
    this.restrictedTime = restrictedTime;
    this.maxLecturerHour = maxLecturerHour;
  }

  public Set<Pair<Integer, Integer>> getRestrictedTime() {
    return restrictedTime;
  }

  public void setRestrictedTime(Set<Pair<Integer, Integer>> restrictedTime) {
    this.restrictedTime = restrictedTime;
  }

  public void addRestrictedTime(int day, int hour) {
    restrictedTime.add(new Pair<>(day, hour));
  }

  public int getMaxLecturerHour() {
    return maxLecturerHour;
  }

  public void setMaxLecturerHour(int maxLecturerHour) {
    this.maxLecturerHour = maxLecturerHour;
  }
}