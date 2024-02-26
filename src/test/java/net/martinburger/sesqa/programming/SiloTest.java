package net.martinburger.sesqa.programming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Harvest;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.Silo;

public class SiloTest {
	@Test
	public void testStore() {
		try {
			Silo testSilo = new Silo(10);
			Harvest testHarvest = new Harvest(10, 0, "Rye");
			assertEquals(0, testSilo.store(testHarvest));
			
			assertEquals(10, testSilo.getFillLevel());
		} catch(Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testTakeOut() {
		try {
			Silo testSilo = new Silo(10);
			Harvest testHarvest = new Harvest(10, 0);
			testSilo.store(testHarvest);
			
			assertEquals(testSilo.takeOut(5), 5);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testStoreFail() {
		try {
			Silo testSilo = new Silo(0);
			Harvest testHarvest = new Harvest(10, 0, "Rye");
			
			assertEquals(10, testSilo.store(testHarvest));
			assertEquals(0, testSilo.getFillLevel());
		} catch(Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testStoreFail2() {
		try {
			Silo testSilo = new Silo(10);
			Harvest testHarvest = new Harvest(10, 0, "Rye");
			Harvest testHarvest2 = new Harvest(10, 0, "Wheat");
			assertEquals(testSilo.store(testHarvest), 0);
			
			assertEquals(10, testSilo.getFillLevel());
			assertEquals(-1, testSilo.store(testHarvest2));
		} catch(Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}
}
