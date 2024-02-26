package net.martinburger.sesqa.programming.codeopolis.domainmodel.plants;

import net.martinburger.sesqa.programming.codeopolis.domainmodel.Conditions;

public abstract class AbstractWinterGrain extends AbstractGrain {
	public AbstractWinterGrain(int baseYield, float allowedTemperatureOffset, float conditionsReduction, float droughtReduction) {
		super(baseYield, true, allowedTemperatureOffset, conditionsReduction, droughtReduction, 3.3f);
	}
	
	@Override
	public final void grow(Conditions conditions) {
		float reduction = this.getConditionsReduction() * (1 - conditions.getSoilConditions());
		
		if(conditions.getAverageTemperatureWinter() < this.getOptimalTempearature()) {
			reduction += this.getConditionsReduction();
		}
		
		this.setYield(this.harvest() - Math.round(this.harvest() * reduction));;
	}
}
