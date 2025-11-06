package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ValidatorTest {
  Validator validator = new Validator();

  @Test
  void 올바른_문장인_경우_통과() {
    assertThatCode(() -> validator.validateUserInput("123;24,53")).doesNotThrowAnyException();

    assertThatCode(() -> validator.validateUserInput("")).doesNotThrowAnyException();

    assertThatCode(() -> validator.validateUserInput("//a\\n1a2a3")).doesNotThrowAnyException();
  }

  @Test
  void 커스텀_구분자가_없는_경우_예외_처리() {
    assertThatThrownBy(() -> validator.validateCustomInput("//\\n1a2a3")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 커스텀_구분자가_숫자인_경우_예외_처리() {
    assertThatThrownBy(() -> validator.validateCustomInput("//12\\n312412512")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 기본_구분자가_아닌_경우_예외_처리() {
    assertThatThrownBy(() -> validator.validateBaseInput("1!2@3$4")).isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> validator.validateBaseInput("4|4!3@")).isInstanceOf(IllegalArgumentException.class);
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
