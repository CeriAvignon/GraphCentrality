package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Simple graph with SpatialNode
 */
public class SpatialGraph extends Graph<SpatialNode, Link>
{
	/**
	 * Links two Nodes
	 * Note : Link will be shared in both nodes
	 * @param inSourceNode
	 * @param inDestinationNode
	 */
	public void linkNode(SpatialNode inSourceNode, SpatialNode inDestinationNode)
	{
		Link NewLink = new Link(inSourceNode, inDestinationNode);
		Links.add(NewLink);
		inSourceNode.addLink(NewLink);
		
		NewLink = new Link(inDestinationNode, inSourceNode);
		inDestinationNode.addLink(NewLink);
	}
	
	/**
	 * Links two Nodes based on indexes
	 * Note : Link will be shared in both nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 */
	public void linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		SpatialNode SourceNode = Nodes.get(inSourceNodeIndex);
		SpatialNode DestinationNode = Nodes.get(inDestinationNodeIndex);

		linkNode(SourceNode, DestinationNode);
	}
	
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
