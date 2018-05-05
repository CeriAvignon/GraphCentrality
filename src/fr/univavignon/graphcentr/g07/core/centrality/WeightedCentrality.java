package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on weighted graph
 */
public interface WeightedCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(WeightedGraph inGraph);
}
