package fr.univavignon.graphcentr.g07.core.helper;

import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.WeightedLink;
import fr.univavignon.graphcentr.g07.core.Node;

/**
 * 
 * @author Holstein Kelian
 *
 * @param <GraphType> Type of graph
 * @param <NodeType> Type of nodes
 * @brief Provides methods for weighted graphs
 */
public class WeightedGraphHelper 
<
	GraphType extends AbstractGraph<NodeType, WeightedLink>,
	NodeType extends Node
>
{
	/** Attached graph */
	private GraphType graph;
	
	/**
	 * Default constructor
	 * @param inGraph Graph to "help"
	 */
	public WeightedGraphHelper(GraphType inGraph)
	{
		graph = inGraph;
	}
	
	/**
	 * Multiply a graph adjacency matrix by link's weight
	 * @param inMatrix Graph adjacency matrix
	 * @return Adjacency matrix
	 */
	public double[][] toAdjacencyMatrix(double[][] inMatrix)
	{
		for(NodeType CurrentNode : graph.getNodes())
		{
			for(WeightedLink CurrentLink : graph.getNodeLinks(CurrentNode))
			{
				inMatrix[CurrentNode.getIdentifier()][CurrentLink.getDestinationIdentifier()] *= CurrentLink.getWeight();
			}
		}
		
		return inMatrix;
	}
	
	/**
	 * Multiply a graph incidence matrix by link's weight
	 * @param inMatrix Graph incidence matrix
	 * @return Incidence matrix
	 */
	public double[][] toIncidenceMatrix(double[][] inMatrix)
	{
		int LinkIndex = 0;
		for(NodeType CurrentNode : graph.getNodes())
		{
			for(WeightedLink CurrentLink : graph.getNodeLinks(CurrentNode))
			{
				inMatrix[CurrentNode.getIdentifier()][LinkIndex] *= CurrentLink.getWeight();
				inMatrix[CurrentLink.getDestinationIdentifier()][LinkIndex] *= CurrentLink.getWeight();
				LinkIndex++;
			}
		}
		
		return inMatrix;
	}
}
