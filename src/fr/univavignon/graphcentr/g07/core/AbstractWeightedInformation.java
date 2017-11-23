package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Weighted information : class will have a weight
 */
public interface AbstractWeightedInformation 
{
	/**
	 * Set link weight
	 * @param inWeight
	 */
	public void setWeight(double inWeight);
	
	/**
	 * Returns link weight
	 * @return Weight
	 */
	public double getWeight();
}
