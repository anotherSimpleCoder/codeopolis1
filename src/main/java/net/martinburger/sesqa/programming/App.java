package net.martinburger.sesqa.programming;

import java.util.Random;

/**
 * Simple <code>Hello world!</code> application.
 *
 */
public final class App {

  private App() {
    // not called - utility classes should not have a public or default constructor
  }

  /**
   * Prints <code>Hello world!</code> to the console.
   *
   * @param args command line arguments (not used in this application)
   */
  public static void main(final String[] args) {

    int landOffer = (new Random()).nextInt(17,26);
  }
}
