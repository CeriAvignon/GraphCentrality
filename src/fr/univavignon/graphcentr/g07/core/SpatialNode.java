package fr.univavignon.graphcentr.g07.core;

/**
 * @author Holstein Kelian
 * 
 * @brief Type of node with a position (2D)
 */
public class SpatialNode extends Node
{
	/** Node x position */
	private double x = 0.0;
	/** Node y position */
	private double y = 0.0;
	
	/**
	 * Set node x position
	 * @param inX
	 */
	public void setX(double inX)
	{
		x = inX;
	}
	
	/**
	 * Set node y position
	 * @param inY
	 */
	public void setY(double inY)
	{
		y = inY;
	}
	
	/**
	 * Returns node's x position
	 * @return X position
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Returns node's y position
	 * @return Y position
	 */
	public double getY()
	{
		return y;
	}
}
