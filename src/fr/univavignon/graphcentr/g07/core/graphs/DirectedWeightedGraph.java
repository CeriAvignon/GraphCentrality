package fr.univavignon.graphcentr.g07.core.graphs;

import fr.univavignon.graphcentr.g07.core.AbstractDirectedGraph;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.WeightedLink;
import fr.univavignon.graphcentr.g07.core.helper.WeightedGraphHelper;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Directed graph of node & weighted link
 */
public class DirectedWeightedGraph extends AbstractDirectedGraph<Node, WeightedLink> 
{
	/** Weighted graph helper to compute matrices etc... */
	private WeightedGraphHelper<AbstractDirectedGraph<Node, WeightedLink>, Node> weightedGraphHelper;
	
	/**
	 * Default constructor
	 */
	public DirectedWeightedGraph()
	{
		super();
		weightedGraphHelper = new WeightedGraphHelper<>(this);
	}
	
	@Override
	protected void initializeLink(WeightedLink inLink)
	{
		inLink.setWeight(1.0);
	}
	
	/**
	 * Create link and set its weight
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @param inWeight Link's weight
	 * @return Create link
	 */
	public WeightedLink createLink(Node inSourceNode, Node inDestinationNode, double inWeight)
	{
		WeightedLink createdLink = super.createLink(inSourceNode, inDestinationNode);
		createdLink.setWeight(inWeight);
		return createdLink;
	}
	
	/**
	 * Create link and set its weight
	 * @param inSourceNodeIndex Source node's identifier
	 * @param inDestinationNodeIndex Destination node's identifier
	 * @param inWeight Link's weight
	 * @return Create link
	 */
	public WeightedLink createLink(int inSourceNodeIndex, int inDestinationNodeIndex, double inWeight)
	{
		WeightedLink createdLink = super.createLink(inSourceNodeIndex, inDestinationNodeIndex);
		createdLink.setWeight(inWeight);
		return createdLink;
	}
	
	@Override
	public double[][] toAdjacencyMatrix()
	{
		double[][] matrix = super.toAdjacencyMatrix();
		return weightedGraphHelper.toAdjacencyMatrix(matrix);
	}
	
	@Override
	public double[][] toIncidenceMatrix()
	{
		double[][] matrix = super.toIncidenceMatrix();
		return weightedGraphHelper.toIncidenceMatrix(matrix);
	}
}
