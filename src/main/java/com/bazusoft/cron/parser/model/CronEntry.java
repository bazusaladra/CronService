package com.bazusoft.cron.parser.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CronEntry {

  TimeEntry minute;
  TimeEntry hour;
  TimeEntry dayOfMonth;
  TimeEntry month;
  TimeEntry dayOfWeek;
  String command;
}
