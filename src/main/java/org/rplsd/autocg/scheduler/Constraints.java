package org.rplsd.autocg.scheduler;

import java.util.ArrayList;

public class Constraints {
  private static Constraints single_instance = null;
  private ArrayList<Constraint> constraints = new ArrayList<>();

  public static Constraints getInstance() {
    if (single_instance == null)
      single_instance = new Constraints();

    return single_instance;
  }

  public void addConstraint(Constraint constraint) {
    constraints.add(constraint);
  }

  public void removeConstraint(Constraint constraint) {
    constraints.remove(constraint);
  }

  protected Boolean checkConstraints(Schedules schedules, int day, int time) {
    if (this.constraints.isEmpty())
      return true;

    // Notify each of the constraint in the list of registered constraint
    return this.constraints.stream().map((elem) -> elem.isPassed(schedules, day, time)).reduce(Boolean::logicalAnd)
        .get();
  }

  public interface Constraint {
    public boolean isPassed(Schedules schedules, int day, int time);
  }
}