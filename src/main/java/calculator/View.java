package calculator;

import camp.nextstep.edu.missionutils.Console;

public class View {
  public static String input() {
    String input;

    input = Console.readLine();

    return input;
  }

  public static void output(String result) {
    System.out.println(result);
  }
}
