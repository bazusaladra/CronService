package com.bazusoft.cron;

import com.bazusoft.cron.parser.CronEntryParser;
import com.bazusoft.cron.parser.model.CronEntry;
import com.bazusoft.cron.visitor.CronVisitor;

public class CronServiceApplication {

  public static void main(String... args) {

    if (args.length < 1) {
      print(
          "Please pass a valid CRON expression as an argument, for example:",
          "*/15 0 1,15 * 1-5 /usr/bin/find");
      return;
    }

    CronEntryParser cronEntryParser = new CronEntryParser();
    CronVisitor cronVisitor = new CronVisitor();

    try {
      CronEntry cronEntry = cronEntryParser.parseCronEntry(args[0]);
      String output = cronVisitor.print(cronEntry);

      System.out.print(output);

    } catch (Exception e) {
      print("Failed to process given CRON argument:", args[0]);
    }
  }

  private static void print(String... messages) {
    System.err.print(String.join(System.lineSeparator(), messages) + System.lineSeparator());
  }
}
