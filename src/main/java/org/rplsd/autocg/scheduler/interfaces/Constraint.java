package org.rplsd.autocg.scheduler.interfaces;

import org.rplsd.autocg.scheduler.Schedules;

public interface Constraint {
  public boolean isPassed(Schedules schedules, int day, int time);
}
