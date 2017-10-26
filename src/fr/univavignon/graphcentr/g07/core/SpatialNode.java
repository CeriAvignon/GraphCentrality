package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Node with spatial information
 */
public class SpatialNode extends Node implements AbstractSpatialInformation
{
	/**
	 * x position
	 */
	protected double x;
	/**
	 * y position
	 */
	protected double y;
	
	/**
	 * Default constructor
	 */
	public SpatialNode()
	{
		super();
		setPosition(0.0, 0.0);
	}
	
	/**
	 * Set spatial node coords
	 * @param inX
	 * @param inY
	 */
	public SpatialNode(double inX, double inY)
	{
		Links = new Vector<Link>();
		setPosition(inX, inY);
	}

	@Override
	public void setPosition(double inX, double inY) 
	{
		x = inX;
		y = inY;
	}

	@Override
	public double getX() 
	{
		return x;
	}

	@Override
	public double getY() 
	{
		return y;
	}

}
