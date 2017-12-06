package fr.univavignon.graphcentr.g07.core;

/**
 * @author Holstein Kelian
 * 
 * @brief Link between two nodes
 */
public class Node
{
	/** Node identifier in graph */
	private int identifier = -1;
	
	/**
	 * Sets node id
	 * @param inID 
	 */
	public void setIdentifier(int inID)
	{
		identifier = inID;
	}
	
	/**
	 * Returns node id
	 * @return Node's identifier
	 */
	public int getIdentifier()
	{
		return identifier;
	}
	
	@Override
	public String toString()
	{
		return "Node "+identifier;
	}
}
