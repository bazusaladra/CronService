package com.bazusoft.cron.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bazusoft.cron.parser.model.*;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CronEntryParserUnitTest {

  private final CronEntryParser cronEntryParser = new CronEntryParser();

  @Test
  public void shouldParseCronExpressions() {
    String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
    CronEntry cronEntry = cronEntryParser.parseCronEntry(input);
    assertEquals(
        CronEntry.builder()
            .minute(TimeIntervalEntry.builder().interval(15).build())
            .hour(SpecificTimesEntry.builder().times(Set.of(0)).build())
            .dayOfMonth(SpecificTimesEntry.builder().times(Set.of(1, 15)).build())
            .month(AllTimesEntry.builder().build())
            .dayOfWeek(TimeRangeEntry.builder().rangeFrom(1).rangeTo(5).build())
            .command("/usr/bin/find")
            .build(),
        cronEntry);
  }
}
