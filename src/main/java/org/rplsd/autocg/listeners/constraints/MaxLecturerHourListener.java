package org.rplsd.autocg.listeners.constraints;

import java.util.ArrayList;

import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Max_hour_defContext;

import org.rplsd.autocg.scheduler.Constraints;
import org.rplsd.autocg.scheduler.Schedules;

public class MaxLecturerHourListener extends AutoCGBaseListener {
  Constraints constraints = Constraints.getInstance();

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterMax_hour_def(Max_hour_defContext ctx) {
    Integer maxHour = Integer.parseInt(ctx.max_hour_value().children.get(0).toString());
    constraints.addConstraint(new MaxLecturerHour(maxHour));
  }

  public class MaxLecturerHour implements Constraints.Constraint {
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
}
