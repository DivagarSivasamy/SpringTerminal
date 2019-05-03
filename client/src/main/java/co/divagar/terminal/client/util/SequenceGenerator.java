package co.divagar.terminal.client.util;


import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.divagar.terminal.client.config.TerminalConfig;

@Component
public class SequenceGenerator {

  private final TerminalConfig terminalConfig;

  private AtomicInteger atomicInteger;

  @Autowired
  public SequenceGenerator(TerminalConfig terminalConfig) {
    this.terminalConfig = terminalConfig;
    atomicInteger = new AtomicInteger(terminalConfig.getStart());
  }

  public int getNext() {
    return atomicInteger.getAndUpdate(operand -> (operand + 1) % terminalConfig.getEnd());
  }
}
