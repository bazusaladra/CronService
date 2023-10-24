package com.bazusoft.cron.visitor;

import com.bazusoft.cron.parser.model.*;
import java.util.stream.Collectors;

public class CronVisitor {

  private StringBuilder output = new StringBuilder();

  public String print(CronEntry cronEntry) {
    output.append(padLeft("minute"));
    cronEntry.getMinute().acceptVisitor(this, new ClockTimeValues());
    output.append("\n");

    output.append(padLeft("hour"));
    cronEntry.getHour().acceptVisitor(this, new ClockTimeValues());
    output.append("\n");

    output.append(padLeft("day of month"));
    cronEntry.getDayOfMonth().acceptVisitor(this, new DayOfMonthTimeValues());
    output.append("\n");

    output.append(padLeft("month"));
    cronEntry.getMonth().acceptVisitor(this, new MonthTimeValues());
    output.append("\n");

    output.append(padLeft("day of week"));
    cronEntry.getDayOfWeek().acceptVisitor(this, new DayOfWeekTimeValues());
    output.append("\n");

    output.append(padLeft("command"));
    output.append(cronEntry.getCommand());
    output.append("\n");

    return output.toString();
  }

  public void visit(AllTimesEntry allTimesEntry, TimeValues values) {
    output.append(
        values.getAllValues().stream().map(Object::toString).collect(Collectors.joining(" ")));
  }

  public void visit(TimeIntervalEntry timeIntervalEntry, TimeValues values) {
    output.append(
        values.getAllValues().stream()
            .filter(n -> n % timeIntervalEntry.getInterval() == 0)
            .map(Object::toString)
            .collect(Collectors.joining(" ")));
  }

  public void visit(TimeRangeEntry timeRangeEntry, TimeValues values) {
    output.append(
        values.getAllValues().stream()
            .filter(n -> n >= timeRangeEntry.getRangeFrom())
            .filter(n -> n <= timeRangeEntry.getRangeTo())
            .map(Object::toString)
            .collect(Collectors.joining(" ")));
  }

  public void visit(SpecificTimesEntry specificTimesEntry, TimeValues values) {
    output.append(
        values.getAllValues().stream()
            .filter(n -> specificTimesEntry.getTimes().contains(n))
            .map(Object::toString)
            .collect(Collectors.joining(" ")));
  }

  private String padLeft(String input) {
    return String.format("%-14s", input);
  }
}
