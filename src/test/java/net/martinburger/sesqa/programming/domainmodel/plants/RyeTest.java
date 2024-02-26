package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Rye;

public class RyeTest {
	Rye testRye = new Rye();
	
	@Test
	public void testPlant() {
		assertTrue(testRye.plant(10));
		
		int harvest = testRye.harvest();
		assertEquals(20, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureWinter(testRye.getOptimalTemperature());
		
		testRye.grow(c);
		
		assertEquals(oldYield, testRye.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		
		c.setDrought(false);
		
		testRye.drought(c);
		assertEquals(oldYield, testRye.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testRye.plant(10));
		int harvest = testRye.harvest();
		assertEquals(20, harvest);
	}
	
	@Test
	public void testDiseaseOutbreak() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		
		c.setPowdryMildrew(false);
		testRye.diseaseOutbreak(c);
		
		assertEquals(testRye.harvest(), oldYield);
	}
	
	/** Fail tests **/
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureWinter(testRye.getOptimalTemperature()-3);
		
		testRye.grow(c);
		
		float soilReduction = (1 - 0.3f) * testRye.getConditionsReduction();
		float weatherReduction = testRye.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testRye.harvest());
	}

	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testRye.getDroughtReduction());
		
		c.setDrought(true);
		
		testRye.drought(c);
		assertEquals(referenceYield, testRye.harvest());
	}
	
	@Test
	public void testDiseaseOutbreakFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRye.plant(10));
		int oldYield = testRye.harvest();
		float referenceYield = oldYield - oldYield * testRye.getPowderyMildrewWinter();
		
		c.setPowdryMildrew(true);
		c.setAverageTemperatureWinter(testRye.getOptimalTemperature() -1);
		
		testRye.diseaseOutbreak(c);
		assertEquals(testRye.harvest(), referenceYield);
	}
}
