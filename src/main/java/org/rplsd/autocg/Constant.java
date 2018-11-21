package org.rplsd.autocg;

import java.util.HashMap;

public class Constant {
  public static final HashMap<String, Integer> WEEKDAYS;
  public static final int DAY_START = 7;
  public static final int DAY_END = 18;

  static {
    WEEKDAYS = new HashMap<>();
    WEEKDAYS.put("MONDAY", 0);
    WEEKDAYS.put("TUESDAY", 1);
    WEEKDAYS.put("WEDNESDAY", 2);
    WEEKDAYS.put("THURSDAY", 3);
    WEEKDAYS.put("FRIDAY", 4);
  };
}