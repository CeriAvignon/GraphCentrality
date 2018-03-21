package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.SpatialWeightedGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on spatial & weighted graph
 */
public interface SpatialWeightedCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(SpatialWeightedGraph inGraph);
}
