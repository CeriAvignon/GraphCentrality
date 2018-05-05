package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedSpatialGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on spatial & directed graph
 */
public interface DirectedSpatialCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(DirectedSpatialGraph inGraph);
}