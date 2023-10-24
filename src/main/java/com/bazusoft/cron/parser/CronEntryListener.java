package com.bazusoft.cron.parser;

import com.bazusoft.cron.parser.antlr.CronBaseListener;
import com.bazusoft.cron.parser.antlr.CronParser;
import com.bazusoft.cron.parser.model.CronEntry;

class CronEntryListener extends CronBaseListener {

  private CronEntry.CronEntryBuilder cronEntryBuilder;

  public CronEntry getCronEntry() {
    return cronEntryBuilder.build();
  }

  @Override
  public void enterCronEntry(CronParser.CronEntryContext ctx) {
    cronEntryBuilder = CronEntry.builder();
  }

  @Override
  public void enterMinute(CronParser.MinuteContext ctx) {
    cronEntryBuilder.minute(ctx.getText());
  }

  @Override
  public void exitMinute(CronParser.MinuteContext ctx) {
    String text = ctx.getText();
  }
}
