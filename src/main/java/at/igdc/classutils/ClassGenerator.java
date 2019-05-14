package at.igdc.classutils;

import javassist.*;

public class ClassGenerator {
  private static final String EXCEPTION_BASE_CLASS = "java.lang.Exception";

  public static Class<? extends Exception> generateExceptionClass(String name) throws Exception {
    ClassPool classPool = ClassPool.getDefault();

    CtClass ctClass = classPool.makeClass(name, classPool.getCtClass(EXCEPTION_BASE_CLASS));
    CtConstructor constructor = CtNewConstructor.make(new CtClass[]{classPool.get("java.lang.String")}, null, ctClass);
    ctClass.addConstructor(constructor);

    return (Class<? extends Exception>) ctClass.toClass();
  }

}
