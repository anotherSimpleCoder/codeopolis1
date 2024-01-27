package net.martinburger.sesqa.programming.codeopolis.utils;

public record TurnResult (
		String name,
		int year,
		int newResidents,
		int bushelsHarvested,
		int residents,
		int bushels,
		int starved,
		int acres,
		int ateByRats,
		int starvedPercentage
) {}
