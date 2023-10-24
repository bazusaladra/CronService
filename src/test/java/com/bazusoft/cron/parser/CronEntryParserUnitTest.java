package com.bazusoft.cron.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bazusoft.cron.parser.model.CronEntry;
import org.junit.jupiter.api.Test;

class CronEntryParserUnitTest {

  private final CronEntryParser cronEntryParser = new CronEntryParser();

  @Test
  public void shouldParseCronExpressions() {
    String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
    CronEntry cronEntry = cronEntryParser.parseCronEntry(input);
    assertEquals(CronEntry.builder().minute("*/15").build(), cronEntry);
  }
}
