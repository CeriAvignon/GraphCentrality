package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on directed graph
 */
public interface DirectedCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(DirectedGraph inGraph);
}
