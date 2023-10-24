package com.bazusoft.cron;

import com.bazusoft.cron.parser.CronEntryParser;

public class CronServiceApplication {

  public static void main(String... args) {

    System.out.println(new CronEntryParser().parseCronEntry(args[0]));
  }
}
