package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Corn;

public class CornTest {
	Corn testCorn = new Corn();
	
	@Test
	public void testPlant() {
		assertTrue(testCorn.plant(10));
		
		int harvest = testCorn.harvest();
		assertEquals(40, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureSummer(testCorn.getOptimalTemperatureSummer());
		c.setAverageTemperatureWinter(testCorn.getOptimalTemperatureWinter());
		
		testCorn.grow(c);
		
		assertEquals(oldYield, testCorn.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		
		c.setDrought(false);
		
		testCorn.drought(c);
		assertEquals(oldYield, testCorn.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testCorn.plant(10));
		int harvest = testCorn.harvest();
		assertEquals(40, harvest);
	}
	
	@Test
	public void testPestInfestation() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		
		c.setFritFly(false);
		
		testCorn.pestInfestation(c);
		assertEquals(testCorn.harvest(), oldYield);
	}
	
	@Test
	public void testDiseaseOutbreak() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		
		c.setPowdryMildrew(false);
		
		testCorn.pestInfestation(c);
		assertEquals(testCorn.harvest(), oldYield);
	}
	
	/** Fail test **/
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureSummer(testCorn.getOptimalTemperatureSummer()+3);
		c.setAverageTemperatureWinter(testCorn.getOptimalTemperatureWinter());
		
		testCorn.grow(c);
		
		float soilReduction = (1 - 0.3f) * testCorn.getConditionsReduction();
		float weatherReduction = testCorn.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testCorn.harvest());
	}
	
	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testCorn.getDroughtReduction());
		
		c.setDrought(true);
		
		testCorn.drought(c);
		assertEquals(referenceYield, testCorn.harvest());
	}
	
	@Test
	public void testPestInfestationFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		float referenceYield = Math.round(oldYield - oldYield * (testCorn.getFritFlyReduction()));
		
		c.setFritFly(true);
		
		testCorn.pestInfestation(c);
		assertEquals(testCorn.harvest(), referenceYield);
	}
	
	@Test
	public void testDiseaseOutbreakFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testCorn.plant(10));
		int oldYield = testCorn.harvest();
		float referenceYield = Math.round(oldYield - oldYield * (testCorn.getPowderyMildrewReduction()));
		
		c.setPowdryMildrew(true);
		
		testCorn.diseaseOutbreak(c);
		assertEquals(testCorn.harvest(), referenceYield);
	}
}
