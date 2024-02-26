package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Rice;

public class RiceTest {
	Rice testRice = new Rice();
	@Test
	public void testPlant() {
		assertTrue(testRice.plant(10));
		
		int harvest = testRice.harvest();
		assertEquals(60, harvest);
	}
	
	@Test
	public void testGrow() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		c.setSoilConditions(1);
		c.setAverageTemperatureSummer(testRice.getOptimalTemperature());
		
		testRice.grow(c);
		
		assertEquals(oldYield, testRice.harvest());
	}
	
	@Test
	public void testDrought() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		c.setDrought(false);
		
		testRice.drought(c);
		assertEquals(oldYield, testRice.harvest());
	}
	
	@Test
	public void testHarvest() {
		assertTrue(testRice.plant(10));
		int harvest = testRice.harvest();
		assertEquals(60, harvest);
	}
	
	@Test
	public void testPestInfestation() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		c.setDelioFly(false);
		c.setBarleyGoutFly(false);
		
		testRice.pestInfestation(c);
		assertEquals(testRice.harvest(), oldYield);
	}
	
	@Test
	public void testDiseaseOutbreak() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		c.setLeafDrought(false);
		c.setFusarium(false);
		
		testRice.diseaseOutbreak(c);
		assertEquals(testRice.harvest(), oldYield);
	}
	
	/** Failures **/
	@Test
	public void testGrowFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		c.setSoilConditions(0.3f);
		c.setAverageTemperatureSummer(testRice.getOptimalTemperature()+3);
		
		testRice.grow(c);
		
		float soilReduction = (1 - 0.3f) * testRice.getConditionsReduction();
		float weatherReduction = testRice.getConditionsReduction();
		float referenceYield = Math.round(oldYield - oldYield * (soilReduction + weatherReduction));
		
		assertEquals(referenceYield, testRice.harvest());
	}
	
	@Test
	public void testDroughtFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		float referenceYield = Math.round(oldYield - oldYield * testRice.getDroughtReduction());
		
		c.setDrought(true);
		
		testRice.drought(c);
		assertEquals(referenceYield, testRice.harvest());
	}
	
	@Test
	public void testPestInfestationFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		
		float temp = testRice.getOptimalTemperature();
		temp += testRice.getOptimalTemperature() * 0.2f;
		
		float referenceYield = Math.round(oldYield - oldYield * (testRice.getDeliaFlyOutsideThresholdReduction() + testRice.getBarleyGoutFlyReduction())) - 1;
		
		c.setDelioFly(true);
		c.setAverageTemperatureSummer(temp);
		c.setBarleyGoutFly(true);
		
		testRice.pestInfestation(c);
		assertEquals(testRice.harvest(), referenceYield);
	}
	
	public void testDiseaseOutbreakFail() {
		Conditions c = Conditions.generateRandomConditions();
		assertTrue(testRice.plant(10));
		int oldYield = testRice.harvest();
		float referenceYield = Math.round(oldYield - oldYield * (testRice.getLeafDroughtRedcution() + testRice.getFusariumReduction()));
		
		c.setLeafDrought(true);
		c.setFusarium(true);
		
		testRice.diseaseOutbreak(c);
		assertEquals(testRice.harvest(), referenceYield);
	}
}
