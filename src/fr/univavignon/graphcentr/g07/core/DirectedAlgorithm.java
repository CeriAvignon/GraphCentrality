package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Inherit from this to create orientedGraph-based algorithms
 */
public class DirectedAlgorithm 
{
	/**
	 * Evaluate given oriented-like-graph
	 * @param inGraph Graph to evaluate
	 * @return AlgorithmResult
	 */
	public <GraphNodeType extends Node, GraphLinkType extends Link> 
	AlgorithmResult evaluate(DirectedGraph<GraphNodeType, GraphLinkType> inGraph) 
	{
		AlgorithmResult Result = new AlgorithmResult("Leaf nodes (Node: Index)");
		
		for(int i = 0; i < inGraph.getNodeCount(); i++)
		{
			Node CurrentNode = inGraph.getNodeAt(i);
			if(CurrentNode.getLinkCount() == 0)
				Result.add(CurrentNode, i);
		}

		return Result;
	}	
}
