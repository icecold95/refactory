package at.igdc.classutils;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassFinder {
  public static <T> List<Class<? extends T>> findSubClasses(String packagePrefix, Class<? extends T> clazz) {
    Reflections reflections = new Reflections(packagePrefix);
    return new ArrayList(reflections.getSubTypesOf(clazz));
  }

  public static List<Class<? extends Exception>> findExceptionClasses() {
    return findSubClasses("java.lang", Exception.class);
  }

  public static Class<? extends Exception> findRandomExceptionClass() {
    List<Class<? extends Exception>> exceptionClasses = findExceptionClasses();
    return exceptionClasses.get(new Random().nextInt(exceptionClasses.size()));
  }
}
