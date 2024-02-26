package net.martinburger.sesqa.programming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.City;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

public class CityTest {
    @Test
    public void testBuy() {
        //Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        //int oldBushels = testCity.getBushels();
        int oldFillLevel = testCity.getDepot().getFillLevel();
        int oldAcres = testCity.getAcres();

        //Run buy method
        int landPrice = 19;
        int acresInput = 2;
        assertDoesNotThrow(()->testCity.buyLand(landPrice, acresInput));
        //assertTrue(oldBushels >= testCity.getBushels());
        assertTrue(testCity.getDepot().getFillLevel() < oldFillLevel);
        assertTrue(oldAcres <= testCity.getAcres());
    }

    @Test
    public void testSell() {
        //Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        int oldBushels = testCity.getBushels();
        int oldAcres = testCity.getAcres();

        //Run sell method
        int landPrice = 19;
        int acresToBeSold = 10;
        assertDoesNotThrow(()->testCity.sellLand(landPrice, acresToBeSold));

        assertTrue(oldBushels <= testCity.getBushels());
        assertTrue(oldAcres >= testCity.getAcres());
    }

    @Test
    public void testFeed() {
        //Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        int oldFillLevel = testCity.getDepot().getFillLevel();

        //Run feed method
        assertDoesNotThrow(()->testCity.feed(10));
        assertTrue(testCity.getDepot().getFillLevel() < oldFillLevel);
    }
    
    @Test
    public void testPlant() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        
        int oldFillLevel = testCity.getDepot().getFillLevel();
        
        int acresToPlant[] = {10,20,30,10,10,20};
        int totalAcresToPlant = Arrays.stream(acresToPlant).reduce(0, (subtotal, element)-> subtotal += element);
        
        assertDoesNotThrow(()-> testCity.plant(10, 1, acresToPlant));
        assertEquals(testCity.getPlantedAcres(), totalAcresToPlant);
        assertTrue(testCity.getDepot().getFillLevel() < oldFillLevel);
    }
    
    @Test
    public void testCalculateStarvation() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        testCity.setBushelsFed(10);
        
        int starvation = testCity.calculateStarvation(10);
        assertEquals(starvation, 99);
    }
    
    @Test
    public void testCalculateStarvedPercentage() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        testCity.setBushelsFed(10);
        
        int starvation = testCity.calculateStarvation(10);
        assertEquals(starvation, 99);
        
        int starvedPercentage = testCity.calculateStarvedPercentage(starvation);
        assertEquals(starvedPercentage, 99);
    }

    public void testCalculateNewResidents() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        testCity.setBushelsFed(500);
        
        int starvation = testCity.calculateStarvation(10);
        assertEquals(starvation, 50);
        
        int starvedPercentage = testCity.calculateStarvedPercentage(starvation);
        assertEquals(starvedPercentage, 50);
        
        int newResidents = testCity.calculateNewResidents(0.3f, starvedPercentage);
        assertEquals(newResidents, 30);
    }
    
    @Test
    public void testCalculateNewHarvest() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        int acresToPlant[] = {10,20,30,10,10,20};
        Conditions testConditions = Conditions.generateRandomConditions();
        testConditions.setSoilConditions(1.0f);
        testConditions.setAverageTemperatureSummer(18.0f);
        testConditions.setAverageTemperatureWinter(3.3f);
        testConditions.setDrought(false);
        testConditions.setFusarium(false);
        testConditions.setLeafDrought(false);
        testConditions.setPowdryMildrew(false);
        testConditions.setBarleyGoutFly(false);
        testConditions.setDelioFly(false);
        testConditions.setFritFly(false);
        
        assertDoesNotThrow(()-> testCity.plant(10, 1, acresToPlant));
        
        int newHarvest = testCity.calculateNewHarvest(0.5f, 1, testConditions);
        assertEquals(newHarvest, 50);
    }
    
    @Test
    public void testCalculateEatenByRats() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        testCity.setBushels(10);
        
        int eatenByRats = testCity.calculateEatenByRats(50.0f);
        assertEquals(eatenByRats, 5);
    }
    
    //Fail tests
    @Test
    public void testBuyFail() {
        City testCity = new City("Test city", 100, 1000, 2800, 30);

        int wantedAcres = 290;
        assertThrows(Exception.class, ()->testCity.buyLand(10, wantedAcres));
        
        //Test for negative input
        int randomNegative = (new Random()).nextInt(-100, -1);
        assertThrows(Exception.class, ()->testCity.buyLand(10, randomNegative));
    }

    @Test
    public void testSellFail() {
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        
        int acresToSell = testCity.getAcres() + 10;
        assertThrows(Exception.class, ()-> testCity.sellLand(10, acresToSell));

        //Test for negative input
        int randomNegative = (new Random()).nextInt(-100, -1);
        assertThrows(Exception.class, ()->testCity.sellLand(10, randomNegative));
    }

    @Test
    public void testFeedFail() {
    	City testCity = new City("Test city", 100, 1000, 0, 30);
    	testCity.setBushels(0);
    	
    	assertThrows(Exception.class, ()->testCity.feed(10));
    	
        //Test for negative input
        int randomNegative = (new Random()).nextInt(-100, -1);
        assertThrows(Exception.class, ()->testCity.feed(randomNegative));
    }
    
    @Test
    public void testPlantFail() {
    	City testCity = new City("Test city", 100, 1000, 0, 30);
    	testCity.setBushels(0);
    	
    	int[] acresToPlant = {100, 100, 100, 100, 100, 100};
    	assertThrows(Exception.class, ()->testCity.plant(10, 1, acresToPlant));
    	
        //Test for negative input
    	int[] negativeAcresToPlant = Arrays.stream(acresToPlant).map((element)->(new Random()).nextInt(-100, -1)).toArray();
       // int randomNegative = (new Random()).nextInt(-100, -1);
        assertThrows(Exception.class, ()->testCity.plant(-10, 1, negativeAcresToPlant));
    }
    
    @Test
    public void testCalculateNewHarvestFail() {
    	//Create test city
        City testCity = new City("Test city", 100, 1000, 2800, 30);
        int acresToPlant[] = {10,20,30,10,10,20};
        Conditions testConditions = Conditions.generateRandomConditions();
        testConditions.setSoilConditions(1.0f);
        testConditions.setAverageTemperatureSummer(17.0f);
        testConditions.setAverageTemperatureWinter(3.2f);
        testConditions.setDrought(true);
        testConditions.setFusarium(false);
        testConditions.setLeafDrought(true);
        testConditions.setPowdryMildrew(false);
        testConditions.setBarleyGoutFly(true);
        testConditions.setDelioFly(false);
        testConditions.setFritFly(true);
        
        assertDoesNotThrow(()-> testCity.plant(10, 1, acresToPlant));
        
        int newHarvest = testCity.calculateNewHarvest(0.5f, 1, testConditions);
        assertNotEquals(newHarvest, 50);
    }
}
