package org.rplsd.autocg.listeners.constraints;

import java.util.ArrayList;

import org.rplsd.autocg.scheduler.Constants;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Restricted_defContext;

import org.rplsd.autocg.scheduler.Constraints;
import org.rplsd.autocg.scheduler.Schedules;

public class RestrictedTimeListener extends AutoCGBaseListener {
  Constraints constraints = Constraints.getInstance();

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterRestricted_def(Restricted_defContext ctx) {

    ArrayList<ArrayList<Boolean>> restricted = new ArrayList<>(Constants.WEEKDAYS.size());

    for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
      ArrayList<Boolean> temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);

      for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
        temp.add(true);
      }

      restricted.add(temp);
    }

    for (int i = 0; i < ctx.restricted_val_def().size(); i++) {
      for (int j = 0; j < ctx.restricted_val_def(i).value_restricted().size(); j++) {
        Integer start = Integer.parseInt(ctx.restricted_val_def(i).value_restricted(j).children.get(0).toString())
            - Constants.DAY_START;
        Integer end = Integer.parseInt(ctx.restricted_val_def(i).value_restricted(j).children.get(2).toString())
            - Constants.DAY_START;

        for (int idx = start; idx <= end; idx++) {
          restricted
              .get(Constants.WEEKDAYS.get(ctx.restricted_val_def(i).day_name().getChild(0).toString().toUpperCase()))
              .set(idx, false);
        }
      }
    }

    // for (ArrayList<Boolean> tests : restricted) {
    // System.out.println(tests.toString());
    // }

    constraints.addConstraint(new RestrictedTime(restricted));
  }

  public class RestrictedTime implements Constraints.Constraint {
    private ArrayList<ArrayList<Boolean>> restricted;

    public RestrictedTime(ArrayList<ArrayList<Boolean>> restricted) {
      this.restricted = restricted;
    }

    @Override
    public boolean isPassed(Schedules schedules, int day, int time) {
      for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
        for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
          if (schedules.getSchedules().get(i).get(j) != null && !restricted.get(i).get(j)) {
            return false;
          }
        }
      }

      return true;
    }
  }
}
