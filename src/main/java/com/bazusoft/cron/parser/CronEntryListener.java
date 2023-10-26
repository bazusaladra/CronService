package com.bazusoft.cron.parser;

import com.bazusoft.cron.parser.antlr.CronBaseListener;
import com.bazusoft.cron.parser.antlr.CronParser;
import com.bazusoft.cron.parser.model.*;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class CronEntryListener extends CronBaseListener {

  private CronEntry.CronEntryBuilder cronEntryBuilder;
  private TimeEntry.TimeEntryBuilder currentTimeEntryBuilder;
  private Consumer<TimeEntry> currentSetter;

  public CronEntry getCronEntry() {
    return cronEntryBuilder.build();
  }

  @Override
  public void enterCronEntry(CronParser.CronEntryContext ctx) {
    cronEntryBuilder = CronEntry.builder();
  }

  @Override
  public void enterMinute(CronParser.MinuteContext ctx) {
    currentSetter = (p) -> cronEntryBuilder.minute(p);
  }

  @Override
  public void enterHour(CronParser.HourContext ctx) {
    currentSetter = (p) -> cronEntryBuilder.hour(p);
  }

  @Override
  public void enterDayOfMonth(CronParser.DayOfMonthContext ctx) {
    currentSetter = (p) -> cronEntryBuilder.dayOfMonth(p);
  }

  @Override
  public void enterMonth(CronParser.MonthContext ctx) {
    currentSetter = (p) -> cronEntryBuilder.month(p);
  }

  @Override
  public void enterDayOfWeek(CronParser.DayOfWeekContext ctx) {
    currentSetter = (p) -> cronEntryBuilder.dayOfWeek(p);
  }

  @Override
  public void enterAllTimes(CronParser.AllTimesContext ctx) {
    currentSetter.accept(AllTimesEntry.builder().build());
  }

  @Override
  public void enterTimeInteval(CronParser.TimeIntevalContext ctx) {
    currentSetter.accept(
        TimeIntervalEntry.builder().interval(Integer.parseInt(ctx.getText().substring(2))).build());
  }

  @Override
  public void enterTimeRange(CronParser.TimeRangeContext ctx) {
    String[] parts = ctx.getText().split("-");
    int rangeFrom = Integer.parseInt(parts[0]);
    int rangeTo = Integer.parseInt(parts[1]);
    currentSetter.accept(TimeRangeEntry.builder().rangeFrom(rangeFrom).rangeTo(rangeTo).build());
  }

  @Override
  public void enterSpecificTimes(CronParser.SpecificTimesContext ctx) {
    String[] parts = ctx.getText().split(",");
    Set<Integer> times = Arrays.stream(parts).map(Integer::parseInt).collect(Collectors.toSet());
    currentSetter.accept(SpecificTimesEntry.builder().times(times).build());
  }

  @Override
  public void enterCommand(CronParser.CommandContext ctx) {
    cronEntryBuilder.command(ctx.getText());
  }
}
