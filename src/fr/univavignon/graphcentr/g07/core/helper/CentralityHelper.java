package fr.univavignon.graphcentr.g07.core.helper;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;

/**
 * 
 * @author Kelian Holstein
 * Helper to normalise centralities results
 */
public class CentralityHelper 
{
	/**
	 * Returns normalised values 
	 * @param inResult 
	 * @return Normalised values
	 */
	public static CentralityResult getNormalised(CentralityResult inResult)
	{
		CentralityResult Output = new CentralityResult();
		
		if(inResult.size() <= 0)
			return Output;
		
		// Initialise minimum & maximum
		double minimum = inResult.get(0);
		double maximum = inResult.get(0);
		
		for(double currentValue : inResult)
		{
			if(currentValue < minimum)
				minimum = currentValue;
			if(currentValue > maximum)
				maximum = currentValue;
		}
		
		double denominator = maximum - minimum;
		
		// Normalise output
		for(double currentValue : inResult)
			Output.add((currentValue - minimum) / denominator);
		
		return Output;
	}
}
