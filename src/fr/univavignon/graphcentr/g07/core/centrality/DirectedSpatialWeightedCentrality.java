package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedSpatialWeightedGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on spatial, weighted & directed graph
 */
public interface DirectedSpatialWeightedCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(DirectedSpatialWeightedGraph inGraph);
}