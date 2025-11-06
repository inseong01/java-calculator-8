package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParserTest {
  @Test
  void 잘못된_입력을_하면_구분기_생성_예외_발생() {
    assertThatThrownBy(() -> new Parser("1,2#3")).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> new Parser("//\\n1a2a")).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> new Parser("//12\\n112212")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 올바른_입력을_하면_구분기_생성() {
    assertThatCode(() -> new Parser("1,2;3")).doesNotThrowAnyException();

    assertThatCode(() -> new Parser("//a\\n1a2a")).doesNotThrowAnyException();

    assertThatCode(() -> new Parser("// \\n1 2 3")).doesNotThrowAnyException();
  }

  @Test
  void 구분자_추출_성공() {
    String[] inputs = {"1 , 2 ; 3", "// \\n1 2 3", "//!\\n1!2!3"};
    String[] outputs = {",;", " ", "!"};

    for (int i = 0; i < inputs.length; i += 1) {
      Parser parser = new Parser(inputs[i]);

      assertThat(parser.getSeparator()).isEqualTo(outputs[i]);
    }
  }

  @Test
  void 숫자_추출_성공() {
    String[] inputs = {"1 , 2 ; 3", "// \\n2 2 4"};
    int[][] outputs = {{1, 2, 3}, {2, 2, 4}};

    for (int i = 0; i < inputs.length; i += 1) {
      Parser parser = new Parser(inputs[i]);

      String separator = parser.getSeparator();
      int[] numbers = parser.getNumbers(separator);

      assertThat(numbers).isEqualTo(outputs[i]);
    }
  }
}