/**
 * The `Game` class represents the main game logic for the Codeopolis game.
 * It includes methods for initializing the game, running the game loop,
 * and performing various in-game actions such as buying land, selling land,
 * feeding people, planting acres, and processing turns.
 * 
 * @author net.martinburger.sesqa.programming.codeopolis
 * @version 1.0
 */
package net.martinburger.sesqa.programming.codeopolis.domainmodel;

import java.util.Random;

import net.martinburger.sesqa.programming.codeopolis.presentation.TextInterface;
import net.martinburger.sesqa.programming.codeopolis.utils.DifficultyLevel;
import net.martinburger.sesqa.programming.codeopolis.utils.GameConfig;
import net.martinburger.sesqa.programming.codeopolis.utils.JSONService;
import net.martinburger.sesqa.programming.codeopolis.utils.TurnResult;

public class Game {
    private GameConfig gameConfig;
    private City city;
    private UserInterface userInterface;

    /**
     * Constructs a new `Game` object with default settings.
     */
    public Game() {
    	userInterface = new TextInterface();
    	DifficultyLevel difficultyLevel = userInterface.difficultyMenu();
    	
        gameConfig = new GameConfig(difficultyLevel);
        city = new City("Codeopolis", this.gameConfig.getInitialResidents(), this.gameConfig.getInitialAcres(), this.gameConfig.getInitialBushels(), this.gameConfig.getInitialCapacity());
        
    }

    /**
     * Starts the game by displaying the main menu and running the game loop.
     * If the user chooses to play the game, it calls the `runGame` method.
     * Prints a congratulatory message if the game is won.
     */
    public void start() {
        int choice = this.userInterface.mainMenu();

        if (choice == 1) {
            this.runGame();
            this.userInterface.gameWon("Congratulations, you won!");
        } else if(choice == 2) {
            this.loadGame();
        }

        System.exit(0);
    }

    /**
     * Runs the game loop for the specified number of years.
     * Calls various methods in each turn, including buying land,
     * selling land, feeding people, planting acres, and processing the turn.
     * Handles exceptions and exits the game if an error occurs.
     */
    private void runGame() {
        try {
            for (int i = 1; i <= this.gameConfig.getNumberOfYears(); i++) {
                buyLand();
                sellLand();
                feedPeople();
                plantAcres();
                doTurn();
            }
        } catch (Exception e) {
            userInterface.gameLost(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Displays the status menu and prompts the user to buy land.
     * Uses a random number generator to determine land prices.
     * Calls the `buyLand` method of the `City` class.
     * Handles exceptions and prints the stack trace if an error occurs.
     */
    private void buyLand() {
        try {
        	int landPrice = (new Random()).nextInt(this.gameConfig.getMinArcrPrice(), this.gameConfig.getMaxAcrePrice());
        	
            int acresToBuy = this.userInterface.buy(landPrice, this.city.getCityState());
            
            this.city.buyLand(landPrice, acresToBuy);
        } catch (Exception e) {
        	this.userInterface.illegalInput(e.getMessage());
        }
    }

    /**
     * Displays the status menu and prompts the user to sell land.
     * Uses a random number generator to determine land prices.
     * Calls the `sellLand` method of the `City` class.
     * Handles exceptions and prints the stack trace if an error occurs.
     */
    private void sellLand() {
    	while(true) {
            try {
                // If easy mode
                
                int landPrice = (new Random()).nextInt(this.gameConfig.getMinArcrPrice(), this.gameConfig.getMaxAcrePrice());
                int acresToSell = this.userInterface.sell(landPrice, this.city.getCityState());
                
                this.city.sellLand(landPrice, acresToSell);
                return;
                
            } catch (Exception e) {
            	this.userInterface.illegalInput(e.getMessage());
            }
    	}
    }

    /**
     * Displays the status menu and prompts the user to feed the residents.
     * Calls the `feed` method of the `City` class.
     * Handles exceptions and prints the stack trace if an error occurs.
     */
    private void feedPeople() {
        try {
            // If easy mode
            

            int bushelsToFeed = this.userInterface.feed(this.gameConfig.getBushelsPerResident(), this.city.getCityState());

            this.city.feed(bushelsToFeed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Displays the status menu and prompts the user to plant acres for multiple different crops.
     */
    private void plantAcres() {
    	try {
    		int[] acresToPlant = this.userInterface.plant(this.gameConfig.getBushelsPerAcre(), this.gameConfig.getAcrePerResident(), this.city.getCityState());
    		
    		this.city.plant(this.gameConfig.getAcrePerResident(), this.gameConfig.getBushelsPerAcre(), acresToPlant);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }


    /**
     * Executes a game turn by calling the `runTurn` method of the `City` class.
     * Displays the turn result using the `turnResultMenu` method of the `TextInterface` class.
     * Throws an exception and exits the game if certain conditions are met.
     * 
     * @throws Exception If the number of residents becomes zero or if the starved percentage is 50% or higher.
     */
    private void doTurn() {
        TurnResult turnResult = this.city.runTurn(this.gameConfig.getBushelsPerResident(), 
                                                 this.gameConfig.getHarvestFactor(), this.gameConfig.getRateInfestation());
        this.userInterface.turnEnd(turnResult);
        this.storeGame();

        if (turnResult.residents() == 0 || turnResult.starvedPercentage() >= 50) {
            this.userInterface.gameLost("You lost the game!");
        }
    }

    private void loadGame() {
        CityState loadedState = JSONService.cityStateJSONService().read("game.json", this.city.getCityState());
        this.city = new City(loadedState);
    }

    private void storeGame() {
        JSONService.cityStateJSONService().write(this.city.getCityState(), "game.json");
    }
}
