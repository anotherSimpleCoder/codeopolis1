package net.martinburger.sesqa.programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Harvest;

public class HarvestTestDrive {
	@Test
	public void testRemove() {
		Harvest testHarvest = new Harvest(200, 0);
		assertEquals(testHarvest.remove(20), 20);
	}
	
	@Test
	public void testDecay() {
		Harvest testHarvest = new Harvest(200, 0);
		assertEquals(testHarvest.decay(), 0);
	}
	
	@Test
	public void testRemoveFail() {
		Harvest testHarvest = new Harvest(200, 0);
		assertEquals(testHarvest.remove(201), 200);
	}
}