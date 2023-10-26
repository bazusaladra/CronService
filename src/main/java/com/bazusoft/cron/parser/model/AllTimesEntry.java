package com.bazusoft.cron.parser.model;

import com.bazusoft.cron.visitor.CronVisitor;
import com.bazusoft.cron.visitor.TimeValues;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Value
@EqualsAndHashCode(callSuper = true)
public class AllTimesEntry extends TimeEntry {
  @Override
  public void acceptVisitor(CronVisitor visitor, TimeValues values) {
    visitor.visit(this, values);
  }
}
