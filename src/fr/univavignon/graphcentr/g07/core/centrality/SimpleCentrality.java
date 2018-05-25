package fr.univavignon.graphcentr.g07.core.centrality;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief Used to compute centrality on simple graph
 */
public interface SimpleCentrality 
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality results
	 */
	public CentralityResult evaluate(SimpleGraph inGraph);
}
