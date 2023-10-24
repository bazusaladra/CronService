package com.bazusoft.cron.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bazusoft.cron.parser.CronEntryParser;
import com.bazusoft.cron.parser.model.*;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CronVisitorUnitTest {

  private final CronVisitor cronVisitor = new CronVisitor();

  @Test
  public void shouldParseCronExpressions() {
    CronEntry input =
        CronEntry.builder()
            .minute(TimeIntervalEntry.builder().interval(15).build())
            .hour(SpecificTimesEntry.builder().times(Set.of(0)).build())
            .dayOfMonth(SpecificTimesEntry.builder().times(Set.of(1, 15)).build())
            .month(AllTimesEntry.builder().build())
            .dayOfWeek(TimeRangeEntry.builder().rangeFrom(1).rangeTo(5).build())
            .command("/usr/bin/find")
            .build();

    String actualOutput = cronVisitor.print(input);

    assertEquals("""
    minute        0 15 30 45
    hour          0
    day of month  1 15
    month         1 2 3 4 5 6 7 8 9 10 11 12
    day of week   1 2 3 4 5
    command       /usr/bin/find
    """, actualOutput);

  }
}
