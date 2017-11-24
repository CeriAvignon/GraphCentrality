package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Directed graph with weighted links
 */
public class DirectedWeightedGraph extends DirectedGraph<Node, WeightedLink>
{
	/**
	 * Links two nodes and set link's weight
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @param inWeight Link's weight
	 * @return Created link between source node & destination node
	 */
	public WeightedLink createLink(Node inSourceNode, Node inDestinationNode, int inWeight)
	{
		int SourceNodeIndex = Nodes.indexOf(inSourceNode);
		int DestinationNodeIndex = Nodes.indexOf(inDestinationNode);
		
		return createLink(SourceNodeIndex, DestinationNodeIndex, inWeight);
	}

	/**
	 * Links two nodes and set link's weight
	 * @param inSourceNodeIndex Source node index
	 * @param inDestinationNodeIndex Destination node index
	 * @param inWeight Link's weight
	 * @return Created link between source node & destination node
	 */
	public WeightedLink createLink(int inSourceNodeIndex, int inDestinationNodeIndex, int inWeight)
	{
		WeightedLink FirstLink = super.createLink(inSourceNodeIndex, inDestinationNodeIndex);
		FirstLink.setWeight(inWeight);
		WeightedLink SecondLink = getLastLink();
		SecondLink.setWeight(inWeight);
		
		return FirstLink;
	}
}
