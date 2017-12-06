package fr.univavignon.graphcentr.g07.core;

/**
 * @author Holstein Kelian
 * 
 * @brief Type of link with a weight
 */
public class WeightedLink extends Link
{
	/** Link's weight */
	private double weight = 1.0;
	
	/**
	 * Set link weight
	 * @param inWeight
	 */
	public void setWeight(double inWeight)
	{
		weight = inWeight;
	}
	
	/**
	 * Get link weight
	 * @return Link weight
	 */
	public double getWeight()
	{
		return weight;
	}
}
