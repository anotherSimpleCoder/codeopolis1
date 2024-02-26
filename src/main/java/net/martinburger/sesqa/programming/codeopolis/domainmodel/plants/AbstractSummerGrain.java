package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

public abstract class AbstractSummerGrain extends AbstractGrain {
	public AbstractSummerGrain(int baseYield, float allowedTemperatureOffset, float conditionsReduction, float droughtReduction) {
		super(baseYield, false, allowedTemperatureOffset, conditionsReduction, droughtReduction, 18.0f);
	}
	
	@Override
	public final void grow(Conditions conditions) {
		float reduction = this.getConditionsReduction() * (1 - conditions.getSoilConditions());
		
		if(conditions.getAverageTemperatureSummer() > this.getOptimalTempearature()) {
			reduction += this.getConditionsReduction();
		}
		
		this.setYield(this.harvest() - Math.round(this.harvest() * reduction));
	}
}
