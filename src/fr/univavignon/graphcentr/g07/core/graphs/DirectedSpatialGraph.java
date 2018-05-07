package fr.univavignon.graphcentr.g07.core.graphs;

import fr.univavignon.graphcentr.g07.core.AbstractDirectedGraph;
import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.SpatialNode;

import fr.univavignon.graphcentr.g07.core.helper.SpatialGraphHelper;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Directed graph of spatial node & link
 */
public class DirectedSpatialGraph extends AbstractDirectedGraph<SpatialNode, Link> 
{
	/** Spatial graph helper to compute euclidean distance etc... */
	private SpatialGraphHelper<AbstractDirectedGraph<SpatialNode, Link>, Link> spatialGraphHelper;
	
	/**
	 * Default constructor
	 */
	public DirectedSpatialGraph()
	{
		super();
		spatialGraphHelper = new SpatialGraphHelper<>(this);
	}
	
	@Override
	protected void initializeNode(SpatialNode inNode)
	{
		inNode.setX(0.0);
		inNode.setY(0.0);
	}
	
	/**
	 * Create a node and sets its position
	 * @param inX X position
	 * @param inY Y position
	 * @return Created node
	 */
	public SpatialNode createNode(double inX, double inY)
	{
		SpatialNode createdNode = super.createNode();
		createdNode.setX(inX);
		createdNode.setY(inY);
		
		return createdNode;
	}
	
	/**
	 * Returns euclidean distance betweens nodes
	 * @param inSourceNode Source node
	 * @param inDestinationNode Destination node
	 * @return Euclidean distance
	 */
	public double getEuclideanDistance(SpatialNode inSourceNode, SpatialNode inDestinationNode)
	{
		return spatialGraphHelper.getEuclideanDistance(inSourceNode, inDestinationNode);
	}
}