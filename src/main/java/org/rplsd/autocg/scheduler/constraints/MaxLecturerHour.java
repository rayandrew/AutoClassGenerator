package org.rplsd.autocg.scheduler.constraints;

import org.rplsd.autocg.scheduler.Schedules;
import org.rplsd.autocg.scheduler.interfaces.Constraint;

public class MaxLecturerHour implements Constraint {
  private int maxLecturerHour;

  public MaxLecturerHour(int _maxLecturerHour) {
    maxLecturerHour = _maxLecturerHour;
  }

  /**
   * @param maxLecturerHour the maxLecturerHour to set
   */
  public void setMaxLecturerHour(int maxLecturerHour) {
    this.maxLecturerHour = maxLecturerHour;
  }

  /**
   * @return the maxLecturerHour
   */
  public int getMaxLecturerHour() {
    return maxLecturerHour;
  }

  @Override
  public boolean isPassed(Schedules schedules, int day, int time) {
    return true;
  }
}