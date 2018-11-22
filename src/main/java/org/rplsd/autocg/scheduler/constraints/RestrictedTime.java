package org.rplsd.autocg.scheduler.constraints;

import java.util.HashMap;
import java.util.List;

import org.rplsd.autocg.util.Pair;
import org.rplsd.autocg.scheduler.Schedules;
import org.rplsd.autocg.scheduler.interfaces.Constraint;

public class RestrictedTime implements Constraint {
  private HashMap<String, List<Pair<Integer, Integer>>> lecturerPreferredTime = new HashMap<>();

  @Override
  public boolean isPassed(Schedules schedules, int day, int time) {
    return true;
  }
}