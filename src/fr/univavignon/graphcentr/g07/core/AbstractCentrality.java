package fr.univavignon.graphcentr.g07.core;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;

/**
 * 
 * @author Kelian Holstein
 * @brief Interface used to manipulate several centralities of the same type
 * @param <GraphType> Type of graph used
 */
public interface AbstractCentrality <GraphType extends AbstractGraph<?, ?>>
{
	/**
	 * Evaluate given graph
	 * @param inGraph Graph to evaluate
	 * @return Centrality's results
	 */
	public CentralityResult evaluate(GraphType inGraph);
}
