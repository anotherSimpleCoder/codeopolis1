package net.martinburger.sesqa.programming;

import org.junit.jupiter.api.Test;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConditionsTest {
	@Test
	public void testConditions() {
		Conditions x = Conditions.generateRandomConditions();
		assertNotNull(x);
	}
}
