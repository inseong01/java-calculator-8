package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ValidatorTest {
  Validator validator = new Validator();

  @Test
  void 구분자가_있는_경우_통과() {
    assertThatCode(() -> validator.validateSeperator("!")).doesNotThrowAnyException();
  }

  @Test
  void 구분자가_없는_경우_예외_처리() {
    assertThatThrownBy(() -> validator.validateSeperator("")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 구분자가_숫자인_경우_예외_처리() {
    assertThatThrownBy(() -> validator.validateSeperator("12")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 숫자배열이_올바른_경우_통과() {
    int[] numbers = {1, 2, 3, 4, 5};

    assertThatCode(() -> validator.validateNumbers(numbers)).doesNotThrowAnyException();
  }

  @Test
  void 숫자배열이_없는_경우_예외_처리() {
    int[] numbers = {};

    assertThatThrownBy(() -> validator.validateNumbers(numbers)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 숫자배열에서_음수가_포함되어_있는_경우_예외_처리() {
    int[] numbers = {1, 2, 3, -5};

    assertThatThrownBy(() -> validator.validateNumbers(numbers)).isInstanceOf(IllegalArgumentException.class);
  }
}
