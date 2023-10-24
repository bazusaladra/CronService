package com.bazusoft.cron.parser.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CronEntry {

  String minute;
  String hour;
  String dayOfMonth;
  String month;
  String dayOfWeek;
  String command;
}
