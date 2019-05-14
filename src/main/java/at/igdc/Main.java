package at.igdc;

import at.igdc.naming.NameGenerator;

public class Main {

  public static void main(String[] args) throws Exception {
    Exception exception = ExceptionFactory.createException();
    throw exception;
  }

}
