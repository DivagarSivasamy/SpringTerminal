package co.divagar.terminal.client.util;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import co.divagar.terminal.client.config.TerminalConfig;
import co.divagar.terminal.client.util.SequenceGenerator;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class SequenceGeneratorTest {

  private static TerminalConfig terminalConfig = mock(TerminalConfig.class);
  private static SequenceGenerator sequenceGenerator;
  private int expected;

  public SequenceGeneratorTest(int expected) {
    this.expected = expected;
  }

  @Parameterized.Parameters(name = "{index}: getNext()={0}")
  public static Integer[] expectedResults() {
    return new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
  }

  @BeforeClass
  public static void setup() {
    given(terminalConfig.getStart()).willReturn(0);
    given(terminalConfig.getEnd()).willReturn(8);
    sequenceGenerator = new SequenceGenerator(terminalConfig);
  }

  @Test
  public void shouldGenerateCorrectSequenceNumbers() {
    assertEquals(expected, sequenceGenerator.getNext());
  }
}
