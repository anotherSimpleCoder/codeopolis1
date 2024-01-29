package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import net.martinburger.sesqa.programming.codeopolis.utils.TurnResult;

/**
 * The UserInterface interface represents the interface for interactions between
 * the game logic and the user interface.
 * It defines methods for buying, selling, feeding, planting, handling turn end,
 * illegal input, and game over events.
 * 
 * This interface decouples the game logic from the user interface.
 * Dependencies point from the user interface to the game logic, not vice versa.
 * In this way, it is possible to replace the user interface without having to
 * change the game logic.
 */
public interface UserInterface {
	
	int mainMenu();

  /**
   * Allows the user to buy acres of land.
   *
   * @param pricePerAcre The price per acre of land.
   * @param city         The current city.
   * @return The number of acres bought.
   */
  int buy(int pricePerAcre, CityState cityState);

  /**
   * Allows the user to sell acres of land.
   *
   * @param pricePerAcre The price per acre of land.
   * @param city         The current city.
   * @return The number of acres sold.
   */
  int sell(int pricePerAcre, CityState cityState);

  /**
   * Allows the user to feed the city's residents.
   *
   * @param bushelsPerResident The amount of bushels required per resident.
   * @param city               The current city.
   * @return The number of residents fed.
   */
  int feed(int bushelsPerResident, CityState cityState);
  
  /**
   * Allows the user to plant multiple types of crops on acres of land.
   * 
   * @param bushelsPerAcre  The amount of bushels required per acre of land.
   * @param acrePerResident The number of acres required per resident.
   * @param city            The current city.
   * @return The numberS of acres planted per crops.
   */
  int[] plant(int bushelsPerAcre, int acrePerResident, CityState cityState);
  
  /**
   * Allows the user to plant crops on acres of land.
   *
   * @param bushelsPerAcre  The amount of bushels required per acre of land.
   * @param acrePerResident The number of acres required per resident.
   * @param city            The current city.
   * @return The number of acres planted.
     
  int plant(int bushelsPerAcre, int acrePerResident, CityState cityState);
	*/
  
  /**
   * Handles the end of a turn.
   *
   * @param result The result of the turn.
   */
  void turnEnd(TurnResult result);

  /**
   * Handles illegal user input.
   *
   * @param message The error message indicating the illegal input.
   */
  void illegalInput(String message);

  /**
   * Handles the game won event.
   * 
   * @param message The message indicating the result of the game.
   */
  void gameWon(String message);

  /**
   * Handles the game lost event.
   * 
   * @param message The message indicating the result of the game.
   */
  void gameLost(String message);

}
