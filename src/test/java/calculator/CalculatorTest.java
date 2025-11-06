package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
  @Test
  void 숫자를_전달하면_합계_반환() {
    int[][] inputs = {{1, 2, 3}, {2, 3, 4}, {1, 0, 2}};
    int[] outputs = {6, 9, 3};

    for (int i = 0; i < outputs.length; i++) {
      assertThat(Calculator.sum(inputs[i])).isEqualTo(outputs[i]);
    }
  }
}
