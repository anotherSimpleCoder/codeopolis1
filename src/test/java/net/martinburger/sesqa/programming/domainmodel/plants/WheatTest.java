package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Wheat;


public class WheatTest {
	Wheat testWheat = new Wheat();
	
	@Test
	public void testPlant() {
		assertTrue(testWheat.plant(10));
		
		int harvest = testWheat.harvest();
		assertEquals(60, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureWinter(testWheat.getOptimalTemperature());
		
		testWheat.grow(c);
		
		assertEquals(oldYield, testWheat.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		
		c.setDrought(false);
		
		testWheat.drought(c);
		assertEquals(oldYield, testWheat.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testWheat.plant(10));
		int harvest = testWheat.harvest();
		assertEquals(60, harvest);
	}
	
	@Test
	public void testPestInfestation() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		
		c.setFritFly(false);
		c.setBarleyGoutFly(false);

		testWheat.pestInfestation(c);
		assertEquals(testWheat.harvest(), oldYield);
	}
	
	@Test
	public void testDiseaseOutbreak() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		
		c.setPowdryMildrew(false);
		c.setLeafDrought(false);
		
		testWheat.diseaseOutbreak(c);
		assertEquals(testWheat.harvest(), oldYield);
	}
	
	/** Fail tests **/
	
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureWinter(testWheat.getOptimalTemperature()-3);
		
		testWheat.grow(c);
		
		float soilReduction = (1 - 0.3f) * testWheat.getConditionsReduction();
		float weatherReduction = testWheat.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testWheat.harvest());
	}
	
	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testWheat.getDroughtReduction());
		
		c.setDrought(true);
		
		testWheat.drought(c);
		assertEquals(referenceYield, testWheat.harvest());
	}
	
	@Test
	public void testPestInfestationFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		float referenceYield = Math.round(oldYield - oldYield * (testWheat.getFritFlyReduction() + testWheat.getBarleyGoutFlyReduction()));
		
		c.setFritFly(true);
		c.setBarleyGoutFly(true);

		testWheat.pestInfestation(c);
		assertEquals(testWheat.harvest(), referenceYield);
	}
	
	@Test
	public void testDiseaseOutbreakFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testWheat.plant(10));
		int oldYield = testWheat.harvest();
		float referenceYield = Math.round(oldYield - oldYield * (testWheat.getPowderyMildrewReduction() + testWheat.getLeafDroughtWinter()));
		
		c.setAverageTemperatureWinter(testWheat.getOptimalTemperature() - 10);
		c.setPowdryMildrew(true);
		c.setLeafDrought(true);
		
		testWheat.diseaseOutbreak(c);
		assertEquals(testWheat.harvest(), referenceYield);
	}
}
