package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Graph with oriented links
 */
public class OrientedGraph extends Graph<OrientedNode, OrientedLink>
{
	/**
	 * Links two Nodes
	 * Throw runtime exception if linking already linked nodes
	 * @param inSourceNode
	 * @param inDestinationNode
	 */
	public void linkNode(OrientedNode inSourceNode, OrientedNode inDestinationNode)
	{
		if(inDestinationNode.isLinkedWith(inSourceNode))
			throw new RuntimeException("Trying to link already linked nodes");
		
		OrientedLink NewLink = new OrientedLink(inSourceNode, inDestinationNode);
		inSourceNode.addLink(NewLink);
		Links.add(NewLink);
	}
		
	/**
	 * Links two Nodes based on indexes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 */
	public void linkNode(int inSourceNodeIndex, int inDestinationNodeIndex)
	{
		OrientedNode SourceNode = Nodes.get(inSourceNodeIndex);
		OrientedNode DestinationNode = Nodes.get(inDestinationNodeIndex);

		linkNode(SourceNode, DestinationNode);
	}
	
	/**
	 * Create a node and push it in the graph
	 * @return Created node
	 */
	public OrientedNode createNode()
	{
		OrientedNode NewNode = new OrientedNode();
		addNode(NewNode);
		return NewNode;
	}
}
