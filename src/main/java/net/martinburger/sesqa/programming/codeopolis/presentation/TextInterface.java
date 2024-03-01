package net.martinburger.sesqa.programming.codeopolis.presentation;

import java.util.Scanner;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.CityState;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.UserInterface;
import net.martinburger.sesqa.programming.codeopolis.utils.DifficultyLevel;
import net.martinburger.sesqa.programming.codeopolis.utils.TurnResult;

/**
 * This class implements the Text Interface of the Codeopolis game.
 * It takes care of the I/O operations via the CLI.
 *
 * @author net.martinburger.sesqa.programming.codeopolis
 * @version 1.0
 */
public class TextInterface implements UserInterface {
    private Scanner scanner;

    /**
     * Constructs a new TextInterface object with a default Scanner for user input.
     */
    public TextInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the buy land menu and prompts the user for the number of acres to buy.
     *
     * @return The user's input for the number of acres to buy.
     */
    public int buy(int pricePerAcre, CityState cityState) {
        this.statusMenu(cityState);
    	System.out.println();
        System.out.printf("""
                ===== BUY LAND MENU =====
                Current price per acres: %d
                How many acres would you like to buy?        
                """, pricePerAcre);


        
        return this.scanner.nextInt();
    }

    /**
     * Displays the sell land menu and prompts the user for the number of acres to sell.
     *
     * @return The user's input for the number of acres to sell.
     */
    public int sell(int pricePerAcre, CityState cityState) {
    	this.statusMenu(cityState);
    	System.out.println();
        System.out.printf("""
                ===== SELL MENU =====
                Current price per acres: %s
                How many acres would you like to sell?
                """, pricePerAcre);

        return this.scanner.nextInt();
    }

    /**
     * Displays the feed menu and prompts the user for the number of bushels to feed to residents.
     *
     * @return The user's input for the number of bushels to feed.
     */
    public int feed(int bushelsPerResident, CityState cityState) {
    	this.statusMenu(cityState);
    	System.out.println();
    	
        System.out.printf("""
                ===== FEED MENU =====
                How many bushels do you would like to feed to your residents?        
                Bushels needed per resident: %d
                """, bushelsPerResident);
        
        return this.scanner.nextInt();
    }
    
    public int[] plant(int bushelsPerAcre, int acrePerResident, CityState cityState) {
    	String[] plants = {"wheat", "barley", "rye", "millet", "corn", "rice"};
    	int[] acresToPlant = new int[plants.length];
    	
    	this.statusMenu(cityState);
    	System.out.println();
    	
    	System.out.printf("""
                ===== PLANT MENU =====\n
                Bushels per acre: %d \t Acre per resident: %d
                """, bushelsPerAcre, acrePerResident);
    	
    	for(int i = 0; i < plants.length; i++) {
    		System.out.printf("Enter the amount of %s :", plants[i]);
    		acresToPlant[i] = scanner.nextInt();
    		System.out.println();
    	}
    	
    	return acresToPlant;
    }

    /**
     * Displays the turn result menu with information about the current turn.
     *
     * @param turnResult The result of the current turn.
     */
    public void turnEnd(TurnResult turnResult) {
        System.out.printf("""
                ===== TURN MENU =====
                Year: %d \t Acres: %d \t Bushels: %d \t Residents: %d
                """, turnResult.year(), turnResult.acres(), turnResult.bushels(), turnResult.residents());
    }
    
    /**
     * Handles illegal user input.
     * */
    public void illegalInput(String message) {
    	System.err.println(message);
    }
    
    /**
     * Handles a successful game.
     * */
    public void gameWon(String message) {
    	System.out.println(message);
    }
    
    /**
     * Handles a failed game.
     * */
    public void gameLost(String message) {
    	System.out.println(message);
    }
    
    /**
     * Extras
     * */
    
    /**
     * Displays the main menu and prompts the user for a choice.
     *
     * @return The user's choice (1 for NEW GAME, 2 for QUIT).
     */
    public int mainMenu() {
        int choice = 0;

        while (true) {
            System.out.print("""
                    ===== MAIN MENU =====
                    1. START
                    2. LOAD GAME
                    3. QUIT
                    Please select an option:""");

            choice = this.scanner.nextInt();

            if (choice == 1 || choice == 2 || choice == 3) {
                return choice;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    /**
     * Displays the status menu with information about the city's current status.
     *
     * @param cityState The City object representing the current state of the city.
     */
    private void statusMenu(CityState cityState) {
        System.out.println("===== STATUS =====");
        System.out.println("City Status: " + cityState.acres() + " of land; " + cityState.bushels() + " of grain; "
                + cityState.population() + " of people.");
        System.out.println();
    }
    
    public DifficultyLevel difficultyMenu() {
    	while(true) {
        	System.out.print("""
        			===== CHOOSE YOUR DIFFICULTY =====
        			1. EASY
        			2. MEDIUM
        			3. HARD
        			Please select an option: """);
        	int choice = this.scanner.nextInt();
        	
        	switch(choice) {
    	    	case 1: {
    	    		return DifficultyLevel.EASY;
    	    	}
    	    	
    	    	case 2: {
    	    		return DifficultyLevel.MEDIUM;
    	    	}
    	    	
    	    	case 3: {
    	    		return DifficultyLevel.HARD;
    	    	}
        	}
    	}
    	
    }
}

