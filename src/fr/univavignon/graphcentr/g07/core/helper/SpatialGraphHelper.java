package fr.univavignon.graphcentr.g07.core.helper;

import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.Link;

/**
 * 
 * @author Holstein Kelian
 *
 * @param <GraphType> Type of graph
 * @param <LinkType> Type of link
 * @brief Provides methods for spatial graphs
 */
public class SpatialGraphHelper 
<
	GraphType extends AbstractGraph<SpatialNode, LinkType>,
	LinkType extends Link
>
{
	/** Attached graph */
	private GraphType graph;
	
	/**
	 * Default constructor
	 * @param inGraph Graph to "help"
	 */
	public SpatialGraphHelper(GraphType inGraph)
	{
		graph = inGraph;
	}
	
	/**
	 * Returns euclidean distance betweens nodes
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @return Euclidean distance
	 */
	public double getEuclideanDistance(SpatialNode inSourceNode, SpatialNode inDestinationNode)
	{
		double X = inSourceNode.getX() - inDestinationNode.getX();
		double Y = inSourceNode.getY() - inDestinationNode.getY();
		X *= X;
		Y *= Y;
		
		return Math.sqrt(X + Y);
	}
}
