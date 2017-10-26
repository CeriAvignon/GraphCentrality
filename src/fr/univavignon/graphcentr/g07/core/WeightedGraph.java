package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Simple graph with weighted links
 */
public class WeightedGraph extends Graph<Node, WeightedLink>
{

	/**
	 * Links two nodes and set link's weight
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @param inWeight Link's weight
	 * @return Created link between source node & destination node
	 */
	public WeightedLink linkNode(Node inSourceNode, Node inDestinationNode, int inWeight)
	{
		int SourceNodeIndex = Nodes.indexOf(inSourceNode);
		int DestinationNodeIndex = Nodes.indexOf(inDestinationNode);
		
		return linkNode(SourceNodeIndex, DestinationNodeIndex, inWeight);
	}

	/**
	 * Links two nodes and set link's weight
	 * @param inSourceNodeIndex Source node index
	 * @param inDestinationNodeIndex Destination node index
	 * @param inWeight Link's weight
	 * @return Created link between source node & destination node
	 */
	public WeightedLink linkNode(int inSourceNodeIndex, int inDestinationNodeIndex, int inWeight)
	{
		WeightedLink FirstLink = super.linkNode(inSourceNodeIndex, inDestinationNodeIndex);
		FirstLink.setWeight(inWeight);
		WeightedLink SecondLink = super.linkNode(inDestinationNodeIndex, inSourceNodeIndex);
		SecondLink.setWeight(inWeight);
		
		return FirstLink;
	}
}
