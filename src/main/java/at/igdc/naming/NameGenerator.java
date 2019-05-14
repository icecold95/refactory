package at.igdc.naming;

import java.util.Random;

public class NameGenerator {
  public static final Random RANDOM = new Random();
  private static final int MIN_PACKAGES = 2;
  private static final int MAX_PACKAGES = 5;

  public static String generateExceptionClassName() {
    return generateClassPrefix() + "Exception";
  }

  public static String generateClassName() {
    return generateClassPrefix() + getRandomElement(NamingConstants.PATTERNS);
  }

  public static String generateMethodName() {
    return getRandomElement(NamingConstants.METHODS);
  }

  private static String generateClassPrefix() {
    return generatePackageName() + "." + getRandomElement(NamingConstants.CLASSES);
  }

  private static String generatePackageName() {
    String domain = getRandomElement(NamingConstants.DOMAINS);
    RandomValuePool<String> randomValuePool = new RandomValuePool<>(NamingConstants.PACKAGES);
    return domain + "." + String.join(".", randomValuePool.nextValues(String.class, RANDOM.nextInt(MAX_PACKAGES - MIN_PACKAGES + 1) + MIN_PACKAGES));
  }

  private static String getRandomElement(String[] array) {
    return array[RANDOM.nextInt(array.length)];
  }

}
