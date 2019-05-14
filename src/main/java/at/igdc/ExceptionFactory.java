package at.igdc;

import at.igdc.classutils.ClassGenerator;
import at.igdc.naming.NameGenerator;

import java.lang.reflect.Constructor;

public class ExceptionFactory {
  private static final int MIN_STACK_SIZE = 3;
  private static final int MAX_STACK_SIZE = 15;
  private static final int MAX_LINE_NUMBER = 2000;

  public static Exception createException() {
    return createException(NameGenerator.generateExceptionClassName(), "yas");
  }

  public static Exception createException(String name, String message) {
    try {
      Class<? extends Exception> clazz = ClassGenerator.generateExceptionClass(name);
      Constructor<? extends Exception> constructor = clazz.getConstructor(String.class);
      Exception e = constructor.newInstance(message);
      enhanceStackTrace(e, NameGenerator.RANDOM.nextInt(MAX_STACK_SIZE - MIN_STACK_SIZE + 1) + MIN_STACK_SIZE);
      return e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void enhanceStackTrace(Exception exception, int complexity) {
    StackTraceElement[] elements = new StackTraceElement[complexity];
    for(int i = 0; i < elements.length; i++) {
      String className = NameGenerator.generateClassName();
      String fileName = className.substring(className.lastIndexOf('.') + 1) + ".java";
      String method = NameGenerator.generateMethodName();
      elements[i] = new StackTraceElement(className, method, fileName, NameGenerator.RANDOM.nextInt(MAX_LINE_NUMBER));
    }
    exception.setStackTrace(elements);
  }

}
