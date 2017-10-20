package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Spatial information : class will have two coords
 */
interface AbstractSpatialInformation
{
	/**
	 * Set node coords
	 * @param X
	 * @param Y
	 */
	public void setPosition(double X, double Y);
	
	/**
	 * Get X position
	 * @return x
	 */
	public double getX();
	
	/**
	 * Get Y position
	 * @return y
	 */
	public double getY();
}
