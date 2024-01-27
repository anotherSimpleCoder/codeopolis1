package net.martinburger.sesqa.programming.domainmodel.plants;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.plants.Grain;

public class GrainTest {
	@Test
	public void testGetDistributedGrainSet() {
		int bushels = 100;
		int[] res = Grain.getDistributedGrainSet(bushels);
		
		int sum = Arrays.stream(res).reduce(0, (subtotal, element)-> subtotal+=element);
		assertEquals(sum, bushels);
	}
	
	@Test
	public void testGetDistributedEmptyGrainSet() {
		int bushels = 0;
		int[] res = Grain.getDistributedGrainSet(bushels);
		
		int sum = Arrays.stream(res).reduce(0, (subtotal, element)-> subtotal+=element);
		assertEquals(sum, bushels);
	}
}
