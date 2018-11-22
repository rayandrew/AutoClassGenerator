package org.rplsd.autocg.listeners;

import java.util.ArrayList;

import org.rplsd.autocg.scheduler.Constants;
import org.rplsd.autocg.scheduler.Lecturers;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Create_lecture_stmtContext;

public class LectureListener extends AutoCGBaseListener {
  @Override
  public void enterCreate_lecture_stmt(Create_lecture_stmtContext ctx) {
    Lecturers lecturers = Lecturers.getInstance();

    ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>(Constants.WEEKDAYS.size());

    for (int i = 0; i < Constants.WEEKDAYS.size(); i++) {
      ArrayList<Boolean> _temp = new ArrayList<>(Constants.DAY_END - Constants.DAY_START);

      for (int j = 0; j < Constants.DAY_END - Constants.DAY_START; j++) {
        _temp.add(false);
      }

      temp.add(_temp);
    }

    System.out.println(ctx.lecture_def().size());

    for (int i = 0; i < ctx.lecture_def().size(); i++) {
      for (int j = 0; j < ctx.lecture_def(i).value_lecture().size(); j++) {
        Integer start = Integer.parseInt(ctx.lecture_def(i).value_lecture(j).children.get(0).toString())
            - Constants.DAY_START;
        Integer end = Integer.parseInt(ctx.lecture_def(i).value_lecture(j).children.get(2).toString())
            - Constants.DAY_START;

        for (int idx = start; idx <= end; idx++) {
          temp.get(Constants.WEEKDAYS.get(ctx.lecture_def(i).day_name().getChild(0).toString().toUpperCase())).set(idx,
              true);
        }
      }
    }

    System.out.println(ctx.lecture_name().any_name().children.get(0).toString());

    for (ArrayList<Boolean> cek : temp) {
      System.out.println(cek.toString());
    }

    lecturers.addLecturer(ctx.lecture_name().any_name().children.get(0).toString(), temp);
  }

  @Override
  public void exitCreate_lecture_stmt(Create_lecture_stmtContext ctx) {

  }
}