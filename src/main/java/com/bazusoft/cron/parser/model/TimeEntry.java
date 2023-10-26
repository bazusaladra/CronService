package com.bazusoft.cron.parser.model;

import com.bazusoft.cron.visitor.CronVisitor;
import com.bazusoft.cron.visitor.TimeValues;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
public abstract class TimeEntry {

  public abstract void acceptVisitor(CronVisitor visitor, TimeValues values);
}
