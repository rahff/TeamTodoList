package org.example.security;

import java.util.Random;

public class RandomCodeGenerator {
  public static String generate() {
    Random random = new Random();
    int min = 100000;
    int max = 999999;
    int generatedCode = random.nextInt(max - min + 1) + min;
    return String.format("%06d", generatedCode);
  }
}

