package org.rplsd.autocg.listeners.constraints;

import java.util.ArrayList;

import org.rplsd.autocg.scheduler.Constants;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Restricted_defContext;

public class RestrictedTimeListener extends AutoCGBaseListener {
  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void enterRestricted_def(Restricted_defContext ctx) {

    ArrayList<ArrayList<Boolean>> restricted = new ArrayList<ArrayList<Boolean>>(Constants.WEEKDAYS.size());

    for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
      ArrayList<Boolean> temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);

      for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
        temp.add(true);
      }

      restricted.add(temp);

    }
    for (int i = 0;i < ctx.restricted_val_def().size();i++) {
      for(int j = 0;j < ctx.restricted_val_def(i).value_restricted().size();j++) {
        Integer start = Integer.parseInt(ctx.restricted_val_def(i).value_restricted(j).children.get(0).toString())
            - Constants.DAY_START;
        Integer end = Integer.parseInt(ctx.restricted_val_def(i).value_restricted(j).children.get(2).toString())
            - Constants.DAY_START;

        for (int idx = start; idx <= end; idx++) {
          restricted.get(Constants.WEEKDAYS.get(ctx.restricted_val_def(i).day_name().getChild(0).toString().toUpperCase())).set(idx,
              false);
        }
      }
    }

    //INSERT DATA TO CLASS
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * The default implementation does nothing.
   * </p>
   */
  @Override
  public void exitRestricted_def(Restricted_defContext ctx) {
  }
}
