package calculator;

import java.util.Arrays;

public class Calculator {
  public static int sum(int[] numbers) {
    return Arrays.stream(numbers).sum();
  }
}
