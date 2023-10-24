package com.bazusoft.acceptance.cron;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bazusoft.cron.CronServiceApplication;
import org.junit.jupiter.api.Test;

class CronServiceAcceptanceTest {

  @Test
  public void shouldPrintHelpIfNoArguments() throws Exception {

    String systemOutput = tapSystemErrAndOut(() -> CronServiceApplication.main());

    assertEquals(
        String.join(
            System.lineSeparator(),
            "Please pass a valid CRON expression as an argument, for example:",
            "*/15 0 1,15 * 1-5 /usr/bin/find",
            ""),
        systemOutput);
  }

  @Test
  public void shouldPrintTheArgumentIfNotValid() throws Exception {
    String input = "*/15";

    String systemOutput = tapSystemErrAndOut(() -> CronServiceApplication.main(input));

    assertEquals(
        String.join(
            System.lineSeparator(),
            "line 1:4 mismatched input '<EOF>' expecting ' '",
            "Failed to process given CRON argument:",
            "*/15",
            ""),
        systemOutput);
  }

  @Test
  public void shouldPrintCronInvocations() throws Exception {
    String input = "*/15 0 1,15 * 1-5 /usr/bin/find";

    String systemOutput = tapSystemErrAndOut(() -> CronServiceApplication.main(input));

    assertEquals(
        """
    minute        0 15 30 45
    hour          0
    day of month  1 15
    month         1 2 3 4 5 6 7 8 9 10 11 12
    day of week   1 2 3 4 5
    command       /usr/bin/find
    """,
        systemOutput);
  }
}
