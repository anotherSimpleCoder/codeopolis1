package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Barley;

public class BarleyTest {
	Barley testBarley = new Barley();
	
	@Test
	public void testPlant() {
		assertTrue(testBarley.plant(10));
		
		int harvest = testBarley.harvest();
		assertEquals(40, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureSummer(testBarley.getOptimalTemperatureSummer());
		c.setAverageTemperatureWinter(testBarley.getOptimalTemperatureWinter());
		
		testBarley.grow(c);
		
		assertEquals(oldYield, testBarley.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		
		c.setDrought(false);
		
		testBarley.drought(c);
		assertEquals(oldYield, testBarley.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testBarley.plant(10));
		int harvest = testBarley.harvest();
		assertEquals(40, harvest);
	}
	
	@Test
	public void testPestInfestation() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		
		c.setBarleyGoutFly(false);
		c.setSoilConditions(0.5f);
		
		testBarley.pestInfestation(c);
		assertEquals(testBarley.harvest(), oldYield);
	}
	
	@Test
	public void testDiseaseOutbreak() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		
		c.setFusarium(false);
		
		testBarley.diseaseOutbreak(c);
		assertEquals(testBarley.harvest(), oldYield);
	}
	
	/** Fail tests **/
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureSummer(testBarley.getOptimalTemperatureSummer());
		c.setAverageTemperatureWinter(testBarley.getOptimalTemperatureWinter()-3);
		
		testBarley.grow(c);
		
		float soilReduction = (1 - 0.3f) * testBarley.getConditionsReduction();
		float weatherReduction = testBarley.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testBarley.harvest());
	}
	
	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testBarley.getDroughtReduction());
		
		c.setDrought(true);
		
		testBarley.drought(c);
		assertEquals(referenceYield, testBarley.harvest());
	}
	
	@Test
	public void testPestInfestationFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		float referenceYield = oldYield - oldYield * (testBarley.getBarleyGoutFlyReduction());
		
		c.setBarleyGoutFly(true);
		
		testBarley.pestInfestation(c);
		assertEquals(testBarley.harvest(), referenceYield);
	}
	
	@Test
	public void testDiseaseOutbreakFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testBarley.plant(10));
		int oldYield = testBarley.harvest();
		float referenceYield = oldYield - oldYield * (testBarley.getFusariumReduction());
		
		c.setFusarium(true);
		
		testBarley.diseaseOutbreak(c);
		assertEquals(testBarley.harvest(), referenceYield);
	}
}
