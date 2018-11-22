package org.rplsd.autocg.scheduler.constraints;

import java.util.ArrayList;

import org.rplsd.autocg.scheduler.Schedules;
import org.rplsd.autocg.scheduler.Constraints.Constraint;;

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
    int count = 0;

    for (ArrayList<Schedules.Schedule> schedule : schedules.getSchedules()) {
      for (Schedules.Schedule sched : schedule) {
        if (sched != null) {
          if (schedules.getLecturers().getLecturers().contains(sched.getLecturer())) {
            count++;
            if (count >= getMaxLecturerHour())
              return false;
          }
        }
      }
    }

    return true;
  }
}