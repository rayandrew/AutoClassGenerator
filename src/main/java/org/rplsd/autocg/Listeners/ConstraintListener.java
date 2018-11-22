package org.rplsd.autocg.listeners;

import java.util.HashMap;

import org.rplsd.autocg.scheduler.Courses;
import org.rplsd.autocg.parser.AutoCGBaseListener;
import org.rplsd.autocg.parser.AutoCGParser.Create_constraint_stmtContext;

public class ConstraintListener extends AutoCGBaseListener {
    @Override public void enterCreate_constraint_stmt(Create_constraint_stmtContext ctx) {
        System.out.println("Masuk");
        System.out.println(ctx.toStringTree());
        System.out.println(ctx.max_hour_def().max_hour().getChild(0));
        System.out.println(ctx.max_hour_def().max_hour_value().getChild(0));

        
        // System.out.println(ctx.restricted_def().restricted_val_def().value_restricted());

        // for (int i = 0;i < ctx.restricted_def().restricted_val_def().size();i++) {
        //     for (int j = 0;j < ctx.restricted_def().restricted_val_def().value_restricted().size();j++) {
        //         System.out.println(ctx.restricted_def().restricted_val_def(i).value_restricted(j));
        //     }
        // }
    }


    @Override public void exitCreate_constraint_stmt(Create_constraint_stmtContext ctx) { 

    }
}


