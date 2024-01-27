/**
 * The `Codeopolis` class serves as the entry point for the Codeopolis game.
 * It contains the `main` method, creating an instance of the `Game` class
 * and initiating the game by calling the `start` method.
 * 
 * @author net.martinburger.sesqa.programming.codeopolis
 * @version 1.0
 */
package net.martinburger.sesqa.programming.codeopolis.presentation;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Game;

public class Codeopolis {
    /**
     * The main method, which serves as the entry point for the Codeopolis game.
     * 
     * @param args The command line arguments (unused in this implementation).
     * @throws Exception If an exception occurs during the execution of the game.
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
