package net.martinburger.sesqa.programming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Depot;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.Harvest;

public class DepotTestDrive {
	@Test
	public void testStore() {
		Depot testDepot = new Depot(24);
		Harvest testHarvest = new Harvest(200, 0);
		
		assertTrue(testDepot.store(testHarvest));
		assertEquals(testDepot.getFillLevel(), 200);
	}
	
	@Test
	public void testTakeOut() {
		Depot testDepot = new Depot(24);
		Harvest testHarvest = new Harvest(200, 0);
		assertTrue(testDepot.store(testHarvest));
		assertEquals(testDepot.getFillLevel(), 200);
		
		assertEquals(testDepot.takeOut(100), 100);
		assertEquals(testDepot.getFillLevel(), 100);
	}
	
	@Test
	public void testExpand() {
		Depot testDepot = new Depot(24);
		testDepot.expand(10);
		
		assertEquals(testDepot.getCapacity(), 34);
	}

	@Test
	public void testDecay() {
		Depot testDepot = new Depot(24);
		Harvest testHarvest = new Harvest(200, 0);
		assertTrue(testDepot.store(testHarvest));
		assertEquals(testDepot.getFillLevel(), 200);
		
		testDepot.decay();
		assertEquals(testDepot.getFillLevel(), 200);
		
	}
}
