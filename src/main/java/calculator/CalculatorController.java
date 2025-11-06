package calculator;

public class CalculatorController {
  private String input;
  private Parser parser;

  void getUserInput() {
    String USER_INPUT_GET_PROMPT = "계산할 문자를 입력해주세요.";

    System.out.println(USER_INPUT_GET_PROMPT);

    this.input = View.input();
  }

  void parseUserInput() {
    try {
      this.parser = new Parser(this.input);
    } catch (Exception e) {
      View.output(e.getMessage());
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  void printUserInput() {
    String USER_OUtPUT_PROMPT = "결과 : ";
    String separator = this.parser.getSeparator();

    int[] numbers = this.parser.getNumbers(separator);

    int result = Calculator.sum(numbers);

    View.output(USER_OUtPUT_PROMPT + result);
  }
}
