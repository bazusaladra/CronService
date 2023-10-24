package com.bazusoft.cron.parser.model;

import com.bazusoft.cron.visitor.CronVisitor;
import com.bazusoft.cron.visitor.TimeValues;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Value
@EqualsAndHashCode(callSuper = true)
public class TimeRangeEntry extends TimeEntry {

  int rangeFrom;
  int rangeTo;

  @Override
  public void acceptVisitor(CronVisitor visitor, TimeValues values) {
    visitor.visit(this, values);
  }
}
