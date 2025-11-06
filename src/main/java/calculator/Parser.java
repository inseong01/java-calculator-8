package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  private final String userInput;
  private String numberString;

  Parser(String input) {
    this.validate(input);
    this.userInput = input;
  }

  private void validate(String input) {
    Validator validator = new Validator();
    validator.validateUserInput(input);
  }

  /**
   * 구분자를 추출하며 가공된 입력을 저장한다.
   *
   * @return 구분자 문자
   */
  public String getSeparator() {
    Pattern customSeparatorPattern = Pattern.compile("^//(.*)\\\\n(.+)");

    Matcher customSeparatorMatcher = customSeparatorPattern.matcher(this.userInput);

    if (customSeparatorMatcher.find()) {
      this.numberString = customSeparatorMatcher.group(2);
      return customSeparatorMatcher.group(1);
    }

    this.numberString = userInput;
    return ",;";
  }

  /**
   * 입력 문자열을 숫자 배열로 반환한다.
   *
   * @param separator 구분자 문자
   * @return 숫자 배열
   */
  public int[] getNumbers(String separator) {
    String[] splitArr = this.numberString.split(String.format("[%s]", separator));

    String[] trimArr = Arrays.stream(splitArr).map(String::trim).toArray(String[]::new);

    return Arrays.stream(trimArr).mapToInt(Integer::parseInt).toArray();
  }
}
