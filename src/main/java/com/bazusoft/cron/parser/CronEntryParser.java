package com.bazusoft.cron.parser;

import com.bazusoft.cron.parser.antlr.CronLexer;
import com.bazusoft.cron.parser.antlr.CronParser;
import com.bazusoft.cron.parser.model.CronEntry;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class CronEntryParser {

  public CronEntry parseCronEntry(String input) {
    CronLexer cronLexer = new CronLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(cronLexer);
    CronParser cronParser = new CronParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    CronEntryListener cronEntryListener = new CronEntryListener();
    walker.walk(cronEntryListener, cronParser.cronEntry());
    return cronEntryListener.getCronEntry();
  }
}
