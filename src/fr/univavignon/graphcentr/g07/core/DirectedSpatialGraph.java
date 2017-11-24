package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Directed graph with spatial nodes
 */
public class DirectedSpatialGraph  extends DirectedGraph<SpatialNode, WeightedLink>
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