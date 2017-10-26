package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Simple graph with SpatialNode
 */
public class SpatialGraph extends Graph<SpatialNode, Link>
{
	
	/**
	 * Create a node and push it in the graph
	 * @param inX X coord
	 * @param inY Y coord
	 * @return Created node
	 */
	public SpatialNode createNode(double inX, double inY)
	{
		SpatialNode NewNode = new SpatialNode(inX, inY);
		addNode(NewNode);
		return NewNode;
	}
}
