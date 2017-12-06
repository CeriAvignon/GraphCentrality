package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.SpatialGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on spatial graph
 */
public interface SpatialCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(SpatialGraph inGraph);
}
