package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Millet;

public class MilletTest {
	Millet testMillet = new Millet();
	
	@Test
	public void testPlant() {
		assertTrue(testMillet.plant(10));
		
		int harvest = testMillet.harvest();
		assertEquals(20, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureSummer(testMillet.getOptimalTemperature());
		
		testMillet.grow(c);
		
		assertEquals(oldYield, testMillet.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		
		c.setDrought(false);
		
		testMillet.drought(c);
		assertEquals(oldYield, testMillet.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testMillet.plant(10));
		int harvest = testMillet.harvest();
		assertEquals(20, harvest);
	}
	
	@Test
	public void testPestInfestation() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		
		c.setBarleyGoutFly(false);
		
		testMillet.pestInfestation(c);
		assertEquals(testMillet.harvest(), oldYield);
	}
	
	/** Fail tests **/
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureSummer(testMillet.getOptimalTemperature()+3);
		
		testMillet.grow(c);
		
		float soilReduction = (1 - 0.3f) * testMillet.getConditionsReduction();
		float weatherReduction = testMillet.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testMillet.harvest());
	}
	
	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testMillet.getDroughtReduction());
		
		c.setDrought(true);
		
		testMillet.drought(c);
		assertEquals(referenceYield, testMillet.harvest());
	}
	
	@Test
	public void testPestInfestationFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testMillet.plant(10));
		int oldYield = testMillet.harvest();
		float referenceYield = oldYield - oldYield * testMillet.getBarleyGoutFlyGoodSoil();
		
		c.setBarleyGoutFly(true);
		c.setSoilConditions(0.9f);
		
		
		testMillet.pestInfestation(c);
		assertEquals(testMillet.harvest(), referenceYield);
	}
}
