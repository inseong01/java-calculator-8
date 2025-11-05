package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
  public static void validateSeperator(String seperator) {
    if (seperator.isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 구분자를 입력해주세요.");
    }

    Pattern numberPattern = Pattern.compile("\\d");

    Matcher numberMatcher = numberPattern.matcher(seperator);

    if (numberMatcher.find()) {
      throw new IllegalArgumentException("[ERROR] 구분자는 숫자 외 문자여야 합니다.");
    }
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
