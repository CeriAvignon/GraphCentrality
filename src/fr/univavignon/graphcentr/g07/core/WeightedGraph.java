package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Simple graph with weighted links
 */
public class WeightedGraph extends Graph<Node, WeightedLink>
{
	/**
	 * Links two Nodes and set link's weight
	 * Note : Link will be shared in both nodes
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @param inWeight Link weight
	 */
	public void linkNode(Node inSourceNode, Node inDestinationNode, int inWeight)
	{
		WeightedLink NewLink = new WeightedLink(inSourceNode, inDestinationNode);
		NewLink.setWeight(inWeight);
		Links.add(NewLink);
		inSourceNode.addLink(NewLink);
		
		NewLink = new WeightedLink(inDestinationNode, inSourceNode);
		NewLink.setWeight(inWeight);
		inDestinationNode.addLink(NewLink);
	}
	
	/**
	 * Links two Nodes based on indexes and set link's weight
	 * Note : Link will be shared in both nodes
	 * @param inSourceNodeIndex
	 * @param inDestinationNodeIndex
	 * @param inWeight 
	 */
	public void linkNode(int inSourceNodeIndex, int inDestinationNodeIndex, int inWeight)
	{
		Node SourceNode = Nodes.get(inSourceNodeIndex);
		Node DestinationNode = Nodes.get(inDestinationNodeIndex);

		linkNode(SourceNode, DestinationNode, inWeight);
	}
	
	/**
	 * Create a node and and it in the graph
	 * @return Created node
	 */
	public Node createNode()
	{
		Node NewNode = new Node();
		addNode(NewNode);
		return NewNode;
	}
}
