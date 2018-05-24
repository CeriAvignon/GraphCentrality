package fr.univavignon.graphcentr.g07.core.graphs;

import fr.univavignon.graphcentr.g07.core.AbstractSimpleGraph;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.WeightedLink;

import fr.univavignon.graphcentr.g07.core.helper.WeightedGraphHelper;
import fr.univavignon.graphcentr.g07.core.helper.SpatialGraphHelper;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Simple graph of spatial node & weighted link
 */
public class SpatialWeightedGraph extends AbstractSimpleGraph<SpatialNode, WeightedLink> 
{
	/** Weighted graph helper to compute matrices etc... */
	private WeightedGraphHelper<AbstractSimpleGraph<SpatialNode, WeightedLink>, SpatialNode> weightedGraphHelper;
	/** Spatial graph helper to compute euclidean distance etc... */
	private SpatialGraphHelper<AbstractSimpleGraph<SpatialNode, WeightedLink>, WeightedLink> spatialGraphHelper;
	
	/**
	 * Default constructor
	 */
	public SpatialWeightedGraph()
	{
		super();
		weightedGraphHelper = new WeightedGraphHelper<>(this);
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
	public WeightedLink createLink(SpatialNode inSourceNode, SpatialNode inDestinationNode, double inWeight)
	{
		WeightedLink createdLink = super.createLink(inSourceNode, inDestinationNode);
		createdLink.setWeight(inWeight);
		lastBackLink.setWeight(inWeight);
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
		lastBackLink.setWeight(inWeight);
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