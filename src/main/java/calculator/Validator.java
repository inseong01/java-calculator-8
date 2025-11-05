package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
  public void validateUserInput(String input) {
    if (!this.isStartCustom(input)) {
      this.validateBaseInput(input);
      return;
    }

    this.validateCustomInput(input);
  }

  public void validateBaseInput(String input) {
    Pattern stringPattern = Pattern.compile("\\D");

    Matcher stringMatcher = stringPattern.matcher(input);

    if (!stringMatcher.find()) return;

    Pattern baseSeparatorPattern = Pattern.compile("^[,;]");

    Matcher baseSeparatorMatcher = baseSeparatorPattern.matcher(input);
    if (!baseSeparatorMatcher.find()) {
      throw new IllegalArgumentException("[ERROR] 기본 구분자로 설정되지 않았습니다.");
    }
  }

  public void validateCustomInput(String input) {
    Pattern customSeparatorPattern = Pattern.compile("^//(.+)\\\\n");

    Matcher customSeparatorMatcher = customSeparatorPattern.matcher(input);

    if (!customSeparatorMatcher.find()) {
      throw new IllegalArgumentException("[ERROR] 커스텀 문자가 설정되지 않았습니다.");
    }

    String customSeparator = customSeparatorMatcher.group(1);

    Pattern numberPattern = Pattern.compile("\\d");

    Matcher numberMatcher = numberPattern.matcher(customSeparator);
    if (numberMatcher.find()) {
      throw new IllegalArgumentException("[ERROR] 구분자는 숫자가 아닌 문자여야 합니다.");
    }
  }

  private Boolean isStartCustom(String input) {
    Pattern customSeparatorPattern = Pattern.compile("^//.*\\\\n.+");

    Matcher customSeparatorMatcher = customSeparatorPattern.matcher(input);

    return customSeparatorMatcher.find();
  }

  public static void validateNumbers(int[] numbers) {
    if (Arrays.stream(numbers).findAny().isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 숫자를 다시 입력해주세요.");
    }

    if (Arrays.stream(numbers).anyMatch(value -> value < 0)) {
      throw new IllegalArgumentException("[ERROR] 음수는 계산할 수 없습니다.");
    }
  }
}
