package com.bazusoft.cron.visitor;

import java.util.List;
import java.util.stream.IntStream;

public class TimeValues {

  private final List<Integer> values;

  protected TimeValues(int rangeFrom, int rangeTo) {
    values = IntStream.rangeClosed(rangeFrom, rangeTo).boxed().toList();
  }

  public List<Integer> getAllValues() {
    return values;
  }
}
