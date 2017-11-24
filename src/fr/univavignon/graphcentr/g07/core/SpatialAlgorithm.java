package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Inherit from this to create spatialGraph-based algorithms
 */
public class SpatialAlgorithm 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return AlgorithmResult
	 */
	public <GraphNodeType extends Node & AbstractSpatialInformation, GraphLinkType extends Link> 
	AlgorithmResult evaluate(AbstractGraph<GraphNodeType, GraphLinkType> inGraph) 
	{	
		// Do stuff
		
		return null;
	}	
}
