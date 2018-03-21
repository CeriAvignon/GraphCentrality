package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on weighted & directed graph
 */
public interface DirectedWeightedCentrality
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(DirectedWeightedGraph inGraph);
}